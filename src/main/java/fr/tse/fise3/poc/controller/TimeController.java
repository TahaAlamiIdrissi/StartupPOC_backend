package fr.tse.fise3.poc.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Collection<Time>> findAllProjects(){
		return new ResponseEntity<Collection<Time>>(this.timeService.findAllTimes(),HttpStatus.OK);
	}
	
	@PostMapping("/time/create")
	public ResponseEntity<Time> createTask(@RequestBody TimeRequest timeRequest) {
		return new ResponseEntity<Time>(this.timeService.createTime(timeRequest),HttpStatus.CREATED) ;
	}
	
	@GetMapping("/time/{userId}")
	public ResponseEntity<Collection<Time>> index(@PathVariable Long userId ) {
		return new ResponseEntity<Collection<Time>>(timeService.getTimeContent(userId),HttpStatus.OK);
	}

}
