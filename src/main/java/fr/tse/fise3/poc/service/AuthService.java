package fr.tse.fise3.poc.service;

import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.AuthenticationResponse;
import fr.tse.fise3.poc.dto.LoginRequest;
import fr.tse.fise3.poc.dto.RegisterRequest;


public interface AuthService {
	
	
	public User signup(RegisterRequest registerRequest) ;
	
	public String generateVerificationToken(User user);
	
	
	public void accountVerification(String token);
	
	
	public AuthenticationResponse login(LoginRequest loginRequest);
	
	
	public User getLoggedInUserInfo(String username);
}
