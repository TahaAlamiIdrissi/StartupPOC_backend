/**
 * 
 */
package fr.tse.fise3.poc.role;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.tse.fise3.poc.service.RoleService;

/**
 * @author root
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleServiceTest {
	
	@Autowired
	private RoleService roleService;

	@Test
	public void testFindAllRoles() {
		assertEquals(3, roleService.findAllRoles().size());
	}

}
