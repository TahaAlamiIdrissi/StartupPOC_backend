/**
 * 
 */
package fr.tse.fise3.poc.time;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.tse.fise3.poc.domain.Time;
import fr.tse.fise3.poc.dto.TimeRequest;
import fr.tse.fise3.poc.service.TimeService;

/**
 * @author root
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeServiceTest {

	@Autowired
	private TimeService timeService;

	@Test
	public void testFindAllTimes() {
		assertEquals(7, timeService.findAllTimes().size());
	}
	
	@Test
	public void testFindTimesById() {
		Time time = timeService.findTimesById(2L).get();
		assertEquals(Double.valueOf(1),Double.valueOf(time.getUser().getUserId()));

	}
	
	@Test
	public void testFindTimesOfUser() {
		assertEquals(3,timeService.findTimesOfUser(1L).size());
	}
	
	@Test
	public void testCreateTime() {
		TimeRequest t = new TimeRequest();
		t.setDateEnd(LocalDateTime.now());
		t.setDateStart(LocalDateTime.now());
		t.setProjectId(1L);
		
		Time tt = timeService.createTime(t);
		assertEquals(Long.valueOf(1L), Long.valueOf(tt.getProject().getId()));
	}
}
