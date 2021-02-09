package fr.tse.fise3.poc.service.impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import fr.tse.fise3.poc.service.AuthService;
import fr.tse.fise3.poc.service.MailService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private final RoleRepository roleRepository;
	
	@Autowired
	private final VerificationTokenRepositoy verificationTokenRepositoy;
	
	@Autowired
	private final MailService mailService;
	
	private final AuthenticationManager authenticationManager;
	
	private final JwtProvider jwtProvider;
	
	private final PasswordEncoder passwordEncoder;
	
	public User signup(RegisterRequest registerRequest) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setEmail(registerRequest.getEmail());
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setFirstname(registerRequest.getFirstname());
		user.setLastname(registerRequest.getLastname());
		user.setFullname(registerRequest.getLastname()+" "+registerRequest.getFirstname());
		user.setRole(roleRepository.findById(registerRequest.getRoleId()).get());
		user.setEnabled(false);
		user.setCreatedAt(Instant.now());
		userRepository.save(user);
		
		String token = generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to <strong>Achieve</strong>, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8081/api/auth/accountVerification/" + token));
		return user;
	}

	public String generateVerificationToken(User user) {
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
	
	public User getLoggedInUserInfo(String username) {
//		UserDetails currentUserDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
		
		return  userRepository.findByUsername(username).get();
	}


}
