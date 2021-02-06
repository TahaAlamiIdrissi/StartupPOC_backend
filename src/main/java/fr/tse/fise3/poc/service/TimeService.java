package fr.tse.fise3.poc.service;

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


public interface TimeService {
	
	public Collection<Time> findAllTimes();
	
	public Optional<Time> findTimesById(Long id);
	
	public Time createTime(TimeRequest timeRequest);
	
	public List<Time> getTimeContent(Long userId); 

	
}
