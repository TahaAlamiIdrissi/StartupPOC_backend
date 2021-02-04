/**
 * 
 */
package fr.tse.fise3.poc.service;

import fr.tse.fise3.poc.domain.Time;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.ChangeUserRequest;

import fr.tse.fise3.poc.dto.CreateUserRequest;
import fr.tse.fise3.poc.dto.UserBody;

/**
 * @author root
 *
 */
public interface UserService {

	User createUser(CreateUserRequest createUserRequest);

	User changeAffectationForUser(ChangeUserRequest changeUserRequest);
	

}
