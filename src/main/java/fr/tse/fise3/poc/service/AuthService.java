package fr.tse.fise3.poc.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.tse.fise3.poc.domain.NotificationEmail;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.domain.VerificationToken;
import fr.tse.fise3.poc.dto.AuthenticationResponse;
import fr.tse.fise3.poc.dto.LoginRequest;
import fr.tse.fise3.poc.dto.RegisterRequest;
import fr.tse.fise3.poc.exception.AchieveNotFoundException;
import fr.tse.fise3.poc.repository.RoleRepository;
import fr.tse.fise3.poc.repository.UserRepository;
import fr.tse.fise3.poc.repository.VerificationTokenRepositoy;
import fr.tse.fise3.poc.security.JwtProvider;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
@Transactional
public class AuthService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	private final VerificationTokenRepositoy verificationTokenRepositoy;
	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	
	public User signup(RegisterRequest registerRequest) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setEmail(registerRequest.getEmail());
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setFirstname(registerRequest.getFirstname());
		user.setLastname(registerRequest.getLastname());
		user.setRole(roleRepository.findById(registerRequest.getRoleId()).get());
		user.setEnabled(false);
		user.setCreatedAt(Instant.now());
		userRepository.save(user);
		
		String token = generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to <strong>Achieve</strong>, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
		return user;
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
	
	public User getLoggedInUserInfo() {
		UserDetails currentUserDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		
		return  userRepository.findByUsername(currentUserDetails.getUsername()).get();
	}

}
