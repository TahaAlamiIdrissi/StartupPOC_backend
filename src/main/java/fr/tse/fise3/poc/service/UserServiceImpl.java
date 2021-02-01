package fr.tse.fise3.poc.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.tse.fise3.poc.domain.Role;
import fr.tse.fise3.poc.domain.User;
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
		user.setPassword(createUserRequest.getPassword());
		user.setEmail(createUserRequest.getEmail());
		user.setFirstname(createUserRequest.getFirstname());
		user.setLastname(createUserRequest.getLastname());
		user.setCreatedAt(Instant.now());
		// Set the user's role
		Role role = roleRepository.findById(createUserRequest.getRoleId()).get();
		user.setRole(role);
		// if this ( MANAGER ) ROLE <= EMPLOYEE ( COMMING FROM the client side)
		User userRole = userRepository.findById(createUserRequest.getCurrentUserId()).get();
		if(userRole.getRole().equals("MANAGER")) {
			user.setManager(userRole);
		}
		
		return userRepository.save(user);
	}
	

}
