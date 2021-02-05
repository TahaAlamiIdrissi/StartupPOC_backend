package fr.tse.fise3.poc.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.tse.fise3.poc.domain.Time;
import fr.tse.fise3.poc.dto.TimeRequest;
import fr.tse.fise3.poc.service.TimeService;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600, methods = { RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH })
public class TimeController {
	
	@Autowired 
	private TimeService timeService;
	
	@GetMapping("/times")
	Collection<Time> findAllProjects(){
		return this.timeService.findAllTimes();
	}
	
	@PostMapping("/time/create")
	Time createTask(@RequestBody TimeRequest timeRequest) {
		return this.timeService.createTime(timeRequest) ;
	}
	
	@GetMapping("/time/{userId}")
	public Time index(@PathVariable Long userId ) {
		
		return timeService.getTimeContent(userId);
	}

}
