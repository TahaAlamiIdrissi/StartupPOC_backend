package fr.tse.fise3.poc.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.tse.fise3.poc.domain.Role;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.ChangeUserRoleRequest;
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
		
		return userRepository.save(user);
	}


	@Override
	public List<User> findUsersofManager(Long idUser) {
		return userRepository.findByManagerUserId(idUser);
	}


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
	

}
