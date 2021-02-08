package fr.tse.fise3.poc.service.impl;

import java.time.LocalDateTime;
import java.time.Month;
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
import fr.tse.fise3.poc.service.TimeService;


@Service
public class TimeServiceImpl  implements TimeService{


	@Autowired
	private UserRepository userRepository;	
	
	@Autowired	
	private ProjectRepository projectRepository;
	
	@Autowired	
	private TimeRepository timeRepository;
	
	@Autowired
	private AuthServiceImpl authService;
	
	
	// Find all time affections of a user 
	@Transactional(readOnly = true)
	public Optional<Time> findTimesById(Long id) {
		
		return this.timeRepository.findById(id);
		
	}
	// Employees can affect time to a certain project on dashboard
		@Transactional
		public Time createTime(TimeRequest timeRequest) {
			

			Time time = new Time();
			
			time.setDateStart(timeRequest.getDateStart());
			time.setCurrentMonth(timeRequest.getDateStart().getMonth());
			time.setDateEnd(timeRequest.getDateEnd());
			User currentUser = this.authService.getLoggedInUserInfo(timeRequest.getUsername());
			time.setUser(currentUser);
			
			Project project = this.projectRepository.findById(timeRequest.getProjectId()).orElse(null);
			time.setProject(project);
			
			return this.timeRepository.save(time);
			
		}

	public Collection<Time> getTimeContent(Long userId) {
		// TODO Auto-generated method stub
		return timeRepository.findAllByUserUserIdAndCurrentMonth(userId,LocalDateTime.now().getMonth());
	}

	// Find all times of a user
	@Override
	public List<Time> findTimesOfUser(Long idUser) {
		return this.timeRepository.findByUserUserId(idUser);
  }
	


	// Find all time affections on database
	@Transactional(readOnly = true)
	public Collection<Time> findAllTimes() {
		return this.timeRepository.findAll();
	}	
	
	@Transactional
	public boolean deleteTime(Long timeId) {
		this.timeRepository.deleteById(timeId);
		return true;
	}

	
}
