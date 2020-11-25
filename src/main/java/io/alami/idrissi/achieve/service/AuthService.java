package io.alami.idrissi.achieve.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.alami.idrissi.achieve.domain.NotificationEmail;
import io.alami.idrissi.achieve.domain.User;
import io.alami.idrissi.achieve.domain.VerificationToken;
import io.alami.idrissi.achieve.dto.AuthenticationResponse;
import io.alami.idrissi.achieve.dto.LoginRequest;
import io.alami.idrissi.achieve.dto.RegisterRequest;
import io.alami.idrissi.achieve.exception.AchieveNotFoundException;
import io.alami.idrissi.achieve.repository.UserRepository;
import io.alami.idrissi.achieve.repository.VerificationTokenRepositoy;
import io.alami.idrissi.achieve.security.JwtProvider;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
@Transactional
public class AuthService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final VerificationTokenRepositoy verificationTokenRepositoy;
	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	
	public void signup(RegisterRequest registerRequest) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setEmail(registerRequest.getEmail());
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setEnabled(false);
		user.setCreatedAt(Instant.now());
		userRepository.save(user);
		
		String token = generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to <strong>Achieve</strong>, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
		
	}

	private String generateVerificationToken(User user) {
		// TODO Auto-generated method stub
		VerificationToken verificationToken = new VerificationToken();
		String token = UUID.randomUUID().toString();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepositoy.save(verificationToken);
		
		return token;
	}
	
	public void accountVerification(String token) {
		Optional<VerificationToken> isToken = verificationTokenRepositoy.findByToken(token);
		isToken.orElseThrow(()-> new AchieveNotFoundException("Token Not found Exception"));
		
		VerificationToken verificationToken = isToken.get();
		User user = verificationToken.getUser();
		user.setEnabled(true);
		userRepository.save(user);
	}

	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String jwtToken = jwtProvider.generateToken(authenticate);
		return new AuthenticationResponse(jwtToken,loginRequest.getUsername());
	}

}
