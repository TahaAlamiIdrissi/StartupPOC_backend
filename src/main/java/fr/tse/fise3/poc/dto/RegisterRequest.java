package fr.tse.fise3.poc.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegisterRequest {
	
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private Instant createdAt;
	private Long roleId;

}