package fr.tse.fise3.poc.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	
	
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userOptional = userRepository.findByUsername(username);
		User user = userOptional.orElseThrow(()-> new UsernameNotFoundException("User not found Exception"));
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true,true, true, getAuthorities("USER"));
	}
	
	
	
	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		// TODO Auto-generated method stub
		return Collections.singletonList(new SimpleGrantedAuthority(role));
	}


}
