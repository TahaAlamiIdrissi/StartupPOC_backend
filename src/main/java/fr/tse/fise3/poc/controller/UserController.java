package fr.tse.fise3.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.ChangeUserRoleRequest;
import fr.tse.fise3.poc.dto.ChangeUserRequest;
import fr.tse.fise3.poc.dto.CreateUserRequest;
import fr.tse.fise3.poc.service.UserService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600, methods = { RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH })

public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/users/create/{idUser}")
	public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest,@PathVariable Long idUser){
		return new ResponseEntity<User>(userService.createUser(createUserRequest, idUser),HttpStatus.CREATED);
	}
	
	@PostMapping("/users/change-role")
	public ResponseEntity<User> changeUserRole(@RequestBody ChangeUserRoleRequest changeUserRoleRequest){
		return new ResponseEntity<User>(userService.changeUserRole(changeUserRoleRequest),HttpStatus.OK);
	}

	
	@PostMapping("/users/change")
	public ResponseEntity<User> changeAffectationUser(@RequestBody ChangeUserRequest changeUserRequest){
		return new ResponseEntity<User>(userService.changeAffectationForUser(changeUserRequest),HttpStatus.OK);
	}

	@GetMapping("/users")
	public Iterable<User> index() {
		return userService.findActiveUsers();
	}

	@PostMapping("/users/edit")
	public ResponseEntity<User> editUser(@RequestBody User user){
		return new ResponseEntity<User>(userService.editUser(user),HttpStatus.OK);
	}
	
	@GetMapping("/users/disable/{idUser}")
	public ResponseEntity<User> disableUser(@PathVariable Long idUser){
		return new ResponseEntity<User>(userService.disableUser(idUser),HttpStatus.OK);
	}
	
	@GetMapping("/users/{idUser}")
	public ResponseEntity<User> getUserInfos(@PathVariable Long idUser){
		return new ResponseEntity<User>(userService.findUser(idUser),HttpStatus.OK);
	}
	
	@GetMapping("/users/manager/{managerId}")
	public ResponseEntity<List<User>> findAllUsersOfManager(@PathVariable Long managerId){
		return new ResponseEntity<List<User>>(userService.findUsersofManager(managerId),HttpStatus.OK);
	}

}
