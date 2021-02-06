package fr.tse.fise3.poc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import fr.tse.fise3.poc.domain.Time;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.TimeRequest;
import fr.tse.fise3.poc.exporter.UserReportExporter;
import fr.tse.fise3.poc.service.TimeService;
import fr.tse.fise3.poc.service.UserService;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600, methods = { RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH })
public class TimeController {
	
	@Autowired 
	private TimeService timeService;
	
	@Autowired 
	private UserService userService;
	
	@GetMapping("/times")
	public Collection<Time> findAllProjects(){
		return this.timeService.findAllTimes();
	}
	
	@PostMapping("/time/create")
	public Time createTask(@RequestBody TimeRequest timeRequest) {
		return this.timeService.createTime(timeRequest) ;
	}
	
	@GetMapping("/time/{userId}")
	public List<Time> index(@PathVariable Long userId ) {
		
		return timeService.getTimeContent(userId);
	}
	
	@GetMapping("/time/{userId}/export/pdf")
	public void exportToPDF(HttpServletResponse response, @PathVariable Long userId ) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=times_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	         
	        List<Time> timesOfUser = timeService.getTimeContent(userId);
	        
	        User user = userService.findUser(userId);
	         
	        UserReportExporter exporter = new UserReportExporter(timesOfUser,user);
	        exporter.export(response);
	         
	    }
	
	

	@PostMapping("/time/user/{userId}")
	Collection<Time> findUserTimesForManager(@PathVariable Long userId) {
		return this.timeService.findTimesOfUser(userId) ;
	}
}
