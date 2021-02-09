package fr.tse.fise3.poc.service;

import java.util.List;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.ChangeUserRoleRequest;
import fr.tse.fise3.poc.dto.ChangeUserRequest;
import fr.tse.fise3.poc.dto.CreateUserRequest;

public interface UserService {
  
	public User createUser(CreateUserRequest createUserRequest,Long idUser);

	public List<User> findUsersofManager(Long idUser);
	
	public User changeUserRole(ChangeUserRoleRequest changeUserRoleRequest);
	
	public User changeAffectationForUser(ChangeUserRequest changeUserRequest);
	
	
	public User findUser(Long idUser);
	
	
	public User disableUser(Long idUser) ;
	
	
	public List<User> findActiveUsers();
	
	
	public User editUser(User user);
	
	

}
