/**
 * 
 */
package fr.tse.fise3.poc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import fr.tse.fise3.poc.utils.RoleUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author root
 *
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

	@Id
	private Long id;
	private String label;
	
}
