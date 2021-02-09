package fr.tse.fise3.poc.domain;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotBlank(message = "username required")
	private String username;
	@NotBlank(message = "email required")
	@Email
	private String email;
	@NotBlank(message = "password required")
	private String password;
	
	private String firstname;
	private String lastname;
	private String fullname;
	private Instant createdAt;
	private boolean enabled;
	
	@ManyToOne
	private User manager;
	// many to one relationship from user to role
	
	@ManyToOne
	private Role role;
	

}
