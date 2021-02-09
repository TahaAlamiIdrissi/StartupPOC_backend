/**
 * 
 */
package fr.tse.fise3.poc.user;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.tse.fise3.poc.ControllerTest;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.ChangeUserRequest;

/**
 * @author root
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UserControllerTest  extends ControllerTest{

	@Test
	public void testGetUserInfos() throws Exception {
		mvc.perform(get("/api/v1/users/"+1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.username", is("johny")));	
	}
	@Test
	public void testFindAllUsersOfManager() throws Exception {
		mvc.perform(get("/api/v1/users/manager/"+2).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].username", is("johny")));	
	}
	
	@Test
	public void testDisableUser() throws Exception {
		mvc.perform(get("/api/v1/users/disable/"+1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.username", is("johny")));	
	}
	
	@Test
	public void testFindActiveUser() throws Exception {
		mvc.perform(get("/api/v1/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].username", is("johny")));	
	}
	
	@Test
	public void testCreateUser() throws Exception {
		User u = new User();
		u.setUsername("lastfirst");
		u.setLastname("lasttest");
		u.setFirstname("firsttest");

		mvc.perform(post("/api/v1/users/create/"+3).contentType(MediaType.APPLICATION_JSON).content(asJsonString(u))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}
	
	@Test
	public void testChangeAffectationUser() throws Exception {
		ChangeUserRequest u = new ChangeUserRequest();
		u.setUserId(2L);
		u.setManagerId(3L);

		mvc.perform(post("/api/v1/users/change/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(u))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}	
}
