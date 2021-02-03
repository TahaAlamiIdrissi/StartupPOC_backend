package fr.tse.fise3.poc.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.tse.fise3.poc.domain.Role;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.CreateUserRequest;
import fr.tse.fise3.poc.repository.RoleRepository;
import fr.tse.fise3.poc.repository.UserRepository;




@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Autowired
	private UserRepository userRepository;	
	
	
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
	

}
