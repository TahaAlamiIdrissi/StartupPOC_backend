/**
 * 
 */
package fr.tse.fise3.poc.dto;

import java.time.Instant;
import fr.tse.fise3.poc.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author root
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
		
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private Instant createdAt;
	
	private Role role;

}
