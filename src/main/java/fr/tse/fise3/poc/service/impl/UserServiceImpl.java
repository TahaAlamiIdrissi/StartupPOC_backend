package fr.tse.fise3.poc.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import fr.tse.fise3.poc.domain.NotificationEmail;
import fr.tse.fise3.poc.domain.Role;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.ChangeUserRoleRequest;
import fr.tse.fise3.poc.domain.VerificationToken;
import fr.tse.fise3.poc.dto.ChangeUserRequest;
import fr.tse.fise3.poc.dto.CreateUserRequest;
import fr.tse.fise3.poc.repository.RoleRepository;
import fr.tse.fise3.poc.repository.UserRepository;
import fr.tse.fise3.poc.repository.VerificationTokenRepositoy;
import fr.tse.fise3.poc.service.MailService;
import fr.tse.fise3.poc.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;	
	
	@Autowired	
	private VerificationTokenRepositoy verificationTokenRepository;
	
	@Autowired
	private MailService mailService;

	
	public User createUser(CreateUserRequest createUserRequest,Long idUser) {

		// save data's coming from inputs
		User user = new User();
		user.setUsername(createUserRequest.getUsername());
		user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
		user.setEmail(createUserRequest.getEmail());
		user.setFirstname(createUserRequest.getFirstname());
		user.setLastname(createUserRequest.getLastname());
		user.setFullname(createUserRequest.getLastname()+" "+createUserRequest.getFirstname());
		user.setCreatedAt(Instant.now());

		// Set the user's role
		
		Role role = roleRepository.findById(createUserRequest.getRoleId()).get();
		user.setRole(role);
		// if this ( MANAGER ) ROLE <= EMPLOYEE ( COMMING FROM the client side)
		//UserDetails currentUserDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication()
          //      .getPrincipal();
		
		User currentUser = userRepository.findById(idUser).get();
		if(currentUser.getRole().getLabel().equals("MANAGER"))
			user.setManager(currentUser);
		
		 if(currentUser.getRole().getLabel().equals("ADMIN")) {
			if(createUserRequest.getManagerId()!=null)
			{User manager = userRepository.findById(createUserRequest.getManagerId()).get();
			user.setManager(manager);}
		}
			
		
		User savedUser = userRepository.save(user);
		String token = generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to <strong>Achieve</strong>, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8081/api/auth/accountVerification/" + token));
		return savedUser;
	}
  
	private String generateVerificationToken(User user) {
		// TODO Auto-generated method stub
		VerificationToken verificationToken = new VerificationToken();
		String token = UUID.randomUUID().toString();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return token;
	}
  
	@Override
	public User changeAffectationForUser(ChangeUserRequest changeUserRequest) {
		
		// get user by id and manager by id
		
		User user = userRepository.findById(changeUserRequest.getUserId()).get();
		User manager = userRepository.findById(changeUserRequest.getManagerId()).get();
		
		//user.getRole().getId() if roleId == 1 then do
		// user.setManager(manager)
		//userRepository.save(User)
		
		if(user.getRole().getId().equals(1L) && manager.getRole().getId().equals(2L)) {
			user.setManager(manager);
		}
		
		return userRepository.save(user);
	}


//	@Override
//	public List<User> findUsersofManager(Long idUser) {
//		return userRepository.findByManagerUserId(idUser);
//	}


	@Override
	public User changeUserRole(ChangeUserRoleRequest changeUserRoleRequest) {
		
		User user = userRepository.findById(changeUserRoleRequest.getUserId()).get();
		Role newRole = roleRepository.findById(changeUserRoleRequest.getRoleId()).get();
		
		//if the current role is manager 
		//we should delete manager for affected users 
		//manager -> admin 
		if (user.getRole().getId().equals(2L) && !newRole.getId().equals(2L)) {
      
			List <User> usersOfManager = findUsersofManager(user.getUserId());
			for (User u :usersOfManager) {
				u.setManager(null);
				userRepository.save(u);				
			}						
		}
    
		//employee -> manager or employee -> admin
		if (user.getRole().getId().equals(1L) && (newRole.getId().equals(2L) || newRole.getId().equals(3L))) {
			user.setManager(null);
			
		}
		
		//affect the new role
		user.setRole(newRole);
		
		return userRepository.save(user);
	}

	@Override
	public User findUser(Long idUser) {
		
		return userRepository.findById(idUser).get();
	}

	//disable user account
	@Override
	public User disableUser(Long idUser) {
		User user = userRepository.findById(idUser).get();
		if (user.getRole().getId().equals(2L)) {
		      
			List <User> usersOfManager = findUsersofManager(user.getUserId());
			for (User u :usersOfManager) {
				u.setManager(null);
				userRepository.save(u);				
			}						
		}
		user.setEnabled(false);
		return userRepository.save(user);		
				
	}

	//return all users with active account
	@Override
	public List<User> findActiveUsers() {
		// TODO Auto-generated method stub
		return userRepository.findByEnabled(true);
	}

	
	@Override
	public List<User> findUsersofManager(Long idManager) {
		return userRepository.findAllByManagerUserId(idManager);
	}

	@Override
	public User editUser(User user_) {
		System.out.println(user_.getEmail());
		System.out.println(user_.getFirstname());
		System.out.println(user_.getRole().getLabel());
		System.out.println("*************");
		System.out.println(user_.getRole().getId());
		System.out.println("**************");
		User user = userRepository.findById(user_.getUserId()).get();
		Role newRole = roleRepository.findById(user_.getRole().getId()).get();
	
		
		//changing infos
		user.setFirstname(user_.getFirstname());
		user.setLastname(user_.getLastname());
		user.setEmail(user_.getEmail());
		user.setUsername(user_.getUsername());
		
		
		// changing role
		if (user.getRole().getId().equals(2L) && !newRole.getId().equals(2L)) {
		      
			List <User> usersOfManager = findUsersofManager(user.getUserId());
			for (User u :usersOfManager) {
				u.setManager(null);
				userRepository.save(u);				
			}						
		}
    
		//employee -> manager or employee -> admin
		if (user.getRole().getId().equals(1L) && (newRole.getId().equals(2L) || newRole.getId().equals(3L))) {
			user.setManager(null);
			
		}
	
		user.setRole(newRole);
		
		
		//changing affectation
		if (user_.getManager()!=null)
		{   User manager = userRepository.findById(user_.getManager().getUserId()).get();
			if(user.getRole().getId().equals(1L) && manager.getRole().getId().equals(2L)) {
			user.setManager(manager);
		}}
		
		
		return userRepository.save(user);	
	}
	
	

}
