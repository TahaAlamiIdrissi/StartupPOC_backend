package fr.tse.fise3.poc.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tse.fise3.poc.domain.Project;
import fr.tse.fise3.poc.domain.Time;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.dto.TimeRequest;
import fr.tse.fise3.poc.repository.ProjectRepository;
import fr.tse.fise3.poc.repository.TimeRepository;
import fr.tse.fise3.poc.repository.UserRepository;


@Service
public class TimeService {

	@Autowired
	private UserRepository userRepository;	
	
	@Autowired	
	private ProjectRepository projectRepository;
	
	@Autowired	
	private TimeRepository timeRepository;
	
	
	// Find all time affections on database
	@Transactional(readOnly = true)
	public Collection<Time> findAllTimes() {
		
		return this.timeRepository.findAll();
	}
	
	// Find all time affections of a user 
	@Transactional(readOnly = true)
	public Optional<Time> findTimesById(Long id) {
		
		return this.timeRepository.findById(id);
		
	}
	
	// Employees can affect time to a certain project on dashboard
	@Transactional
	public Time createTime(TimeRequest timeRequest) {
		
		UserDetails currentUserDetails =(UserDetails) SecurityContextHolder
													.getContext()
													.getAuthentication()
									        .getPrincipal();
		
		User currentUser = userRepository.findByUsername(currentUserDetails.getUsername()).get();
		Time time = new Time();
		
		time.setDateStart(timeRequest.getDateStart());
		time.setDateEnd(timeRequest.getDateEnd());
		time.setUser(currentUser);
		
		Project project = this.projectRepository.findById(timeRequest.getProjectId()).orElse(null);
		time.setProject(project);
		
		return this.timeRepository.save(time);
		
	}

	public Collection<Time> getTimeContent(Long userId) {
		// TODO Auto-generated method stub
		return timeRepository.findAllByUserUserIdAndDateStart(userId,LocalDateTime.now());
	}

	// Find all times of a user
	@Transactional
	public List<Time> findTimesOfUser(Long idUser) {
		return this.timeRepository.findByUserUserId(idUser);
  }
	
}
