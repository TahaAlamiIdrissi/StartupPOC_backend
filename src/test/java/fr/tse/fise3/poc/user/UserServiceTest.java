/**
 * 
 */
package fr.tse.fise3.poc.user;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.ChangeUserRequest;
import fr.tse.fise3.poc.dto.ChangeUserRoleRequest;
import fr.tse.fise3.poc.dto.CreateUserRequest;
import fr.tse.fise3.poc.service.UserService;
import fr.tse.fise3.poc.utils.RoleUtils;

/**
 * @author root
 *
 */	
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
	
	@Autowired
	private UserService userService;

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCreateUser() {
		CreateUserRequest user = new CreateUserRequest();
		user.setUsername("usernameOne");
		user.setPassword("1234567");
		user.setEmail("username@gmail.com");
		user.setFirstname("firstname");
		user.setLastname("lastname");
		user.setCreatedAt(Instant.now());
		user.setRoleId(RoleUtils.MANAGER_ID);
//		User userTest = userService.createUser(user);
//		assertEquals("usernameOne", userTest.getUsername());
	}
	
	@Test
	public void testFindUsersofManager() {
		assertEquals(1, userService.findUsersofManager(2L).size());
	}
	
	
	@Test
	public void testChangeUserRole() {
		ChangeUserRoleRequest c = new ChangeUserRoleRequest();
		c.setUserId(8L);
		c.setRoleId(RoleUtils.MANAGER_ID);
		User u = userService.changeUserRole(c);
		assertEquals(RoleUtils.MANAGER_ID, u.getRole().getId());
		
	}
	
	@Test
	public void testChangeAffectationForUser() {
		ChangeUserRequest c = new ChangeUserRequest();
		c.setUserId(1L);
		c.setManagerId(8L);
		User u = userService.changeAffectationForUser(c);
		
		assertEquals(8, u.getManager().getUserId());
	}
}
