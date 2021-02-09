/**
 * 
 */
package fr.tse.fise3.poc.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

	@Id
	private Long id;
	private String label;
	
}
