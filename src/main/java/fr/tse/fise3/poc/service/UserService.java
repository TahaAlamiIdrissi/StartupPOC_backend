/**
 * 
 */
package fr.tse.fise3.poc.service;

import fr.tse.fise3.poc.domain.Time;
import java.util.List;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.ChangeUserRoleRequest;
import fr.tse.fise3.poc.dto.ChangeUserRequest;
import fr.tse.fise3.poc.dto.CreateUserRequest;

public interface UserService {
  

	public User createUser(CreateUserRequest createUserRequest);
  

	public List<User> findUsersofManager(Long idUser);
	
	
	public User changeUserRole(ChangeUserRoleRequest changeUserRoleRequest);
	
  
	public User changeAffectationForUser(ChangeUserRequest changeUserRequest);
	

}
