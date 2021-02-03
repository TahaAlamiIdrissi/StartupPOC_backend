/**
 * 
 */
package fr.tse.fise3.poc.service;

import java.util.List;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.ChangeUserRoleRequest;
import fr.tse.fise3.poc.dto.CreateUserRequest;

/**
 * @author root
 *
 */
public interface UserService {

	User createUser(CreateUserRequest createUserRequest);
	
	//the parameter is the id of the manager
	public List<User> findUsersofManager(Long idUser);
	
	
	public User changeUserRole(ChangeUserRoleRequest changeUserRoleRequest);
	
}
