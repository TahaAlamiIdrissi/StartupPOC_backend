package fr.tse.fise3.poc.controller;

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
import fr.tse.fise3.poc.dto.AuthenticationResponse;
import fr.tse.fise3.poc.dto.LoginRequest;
import fr.tse.fise3.poc.dto.RegisterRequest;
import fr.tse.fise3.poc.service.AuthService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600, methods = { RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH })
public class AuthController {
	
	
	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody RegisterRequest registerRequest) {
		return new ResponseEntity<User>(authService.signup(registerRequest), HttpStatus.CREATED);
	}
	
	@GetMapping("/accountVerification/{token}")
	public ResponseEntity<String> accountVerification(@PathVariable String token) {
		authService.accountVerification(token);
		return new ResponseEntity<String>("Account enabled successfully \n", HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
	
	@GetMapping("/info/{username}")
	public ResponseEntity<User> loggedInUser(@PathVariable String username) {
		return new ResponseEntity<User>(authService.getLoggedInUserInfo(username),HttpStatus.OK);
	}
	@GetMapping("/test")
	public String index() {
		return "test";
	}
	
	
}
