package fr.tse.fise3.poc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeUserRoleRequest {
	
	private Long userId;
	private Long roleId;

}
