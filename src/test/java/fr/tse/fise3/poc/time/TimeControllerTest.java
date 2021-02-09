/**
 * 
 */
package fr.tse.fise3.poc.time;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.tse.fise3.poc.ControllerTest;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.TimeRequest;


/**
 * @author root
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TimeControllerTest extends ControllerTest{
	
	@Test
	public void testFindAllTimes() throws Exception {
		mvc.perform(get("/api/v1/times").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].dateStart", is("2021-02-05T12:30:07")));
	}
	
	@Test
	public void testGetTimeContentByUserId() throws Exception {
		mvc.perform(get("/api/v1/times/content/"+1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].dateStart", is("2021-02-05T12:30:07")));
	}
	
	@Test
	public void testFindUserTimesForManager() throws Exception {
		mvc.perform(get("/api/v1/times/"+1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].dateStart", is("2021-02-05T12:30:07")));
	}
	
	
	@Test
	public void testCreateTime() throws Exception {
		TimeRequest tr = new TimeRequest();
		
		tr.setDateEnd(LocalDateTime.now());
		tr.setDateStart(LocalDateTime.now());
		tr.setProjectId(3L);
		
		mvc.perform(post("/api/v1/times").contentType(MediaType.APPLICATION_JSON).content(asJsonString(tr))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
//				.andExpect(status().isCreated());
		        
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Test
	public void testDeleteTime() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete("/api/v1/times/"+1)).andReturn();
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
	}
	
}
