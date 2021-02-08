/**
 * 
 */
package fr.tse.fise3.poc.time;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.tse.fise3.poc.domain.Time;
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
}
