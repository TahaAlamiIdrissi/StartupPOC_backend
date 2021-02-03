package fr.tse.fise3.poc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.ChangeUserRoleRequest;
import fr.tse.fise3.poc.dto.ChangeUserRequest;
import fr.tse.fise3.poc.dto.CreateUserRequest;
import fr.tse.fise3.poc.repository.UserRepository;
import fr.tse.fise3.poc.dto.CreateUserRequest;
import fr.tse.fise3.poc.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
  @Autowired
	private UserRepository userRepository;
	
	

	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest){
		return new ResponseEntity<User>(userService.createUser(createUserRequest),HttpStatus.CREATED);
	}
	
	@PostMapping("/change-role")
	public ResponseEntity<User> changeUserRole(@RequestBody ChangeUserRoleRequest changeUserRoleRequest){
		return new ResponseEntity<User>(userService.changeUserRole(changeUserRoleRequest),HttpStatus.OK);
	}

	
	@PostMapping("/change")
	public ResponseEntity<User> changeAffectationUser(@RequestBody ChangeUserRequest changeUserRequest){
		return new ResponseEntity<User>(userService.changeAffectationForUser(changeUserRequest),HttpStatus.OK);
	}
	
	
	@GetMapping("/test")
	public Iterable<User> index() {
		
		return userRepository.findAll();
	}
	
}
