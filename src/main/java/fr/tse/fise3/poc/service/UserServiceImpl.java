package fr.tse.fise3.poc.service;

import java.time.Instant;

import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import fr.tse.fise3.poc.domain.NotificationEmail;
import fr.tse.fise3.poc.domain.Role;
import fr.tse.fise3.poc.domain.Time;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.domain.VerificationToken;
import fr.tse.fise3.poc.dto.ChangeUserRequest;



import fr.tse.fise3.poc.dto.CreateUserRequest;
import fr.tse.fise3.poc.repository.ProjectRepository;
import fr.tse.fise3.poc.repository.RoleRepository;
import fr.tse.fise3.poc.repository.TimeRepository;
import fr.tse.fise3.poc.repository.UserRepository;
import fr.tse.fise3.poc.repository.VerificationTokenRepositoy;




@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Autowired
	private UserRepository userRepository;	
	
	@Autowired	
	private ProjectRepository projectRepository;
	
	@Autowired	
	private TimeRepository timeRepository;
	
	@Autowired	
	private VerificationTokenRepositoy verificationTokenRepository;
	
	@Autowired
	private MailService mailService;

	
	public User createUser(CreateUserRequest createUserRequest) {

		// save data's coming from inputs
		User user = new User();
		user.setUsername(createUserRequest.getUsername());
		user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
		user.setEmail(createUserRequest.getEmail());
		user.setFirstname(createUserRequest.getFirstname());
		user.setLastname(createUserRequest.getLastname());
		user.setCreatedAt(Instant.now());

		// Set the user's role
		Role role = roleRepository.findById(createUserRequest.getRoleId()).get();
		user.setRole(role);
		
		// if this ( MANAGER ) ROLE <= EMPLOYEE ( COMMING FROM the client side)
		UserDetails currentUserDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		
		User currentUser = userRepository.findByUsername(currentUserDetails.getUsername()).get();
		if(currentUser.getRole().getLabel().equals("MANAGER"))
			user.setManager(currentUser);
		
		User savedUser = userRepository.save(user);
		String token = generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to <strong>Achieve</strong>, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
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

	
	
	

}
