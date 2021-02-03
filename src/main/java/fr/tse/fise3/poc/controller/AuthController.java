package fr.tse.fise3.poc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.AuthenticationResponse;
import fr.tse.fise3.poc.dto.LoginRequest;
import fr.tse.fise3.poc.dto.RegisterRequest;
import fr.tse.fise3.poc.repository.RoleRepository;
import fr.tse.fise3.poc.service.AuthService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	
	
	private final AuthService authService;
	private final RoleRepository role;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
		return new ResponseEntity<String>("User has been saved successfully \n" , HttpStatus.OK);
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
	
	@GetMapping("/index")
	public String index() {
		
		return "index \n"+ role.findAll().get(0);
	}
	
	
}
