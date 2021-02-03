/**
 * 
 */
package fr.tse.fise3.poc.service;

import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.CreateUserRequest;

/**
 * @author root
 *
 */
public interface UserService {

	User createUser(CreateUserRequest createUserRequest);
}
