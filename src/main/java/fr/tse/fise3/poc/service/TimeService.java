package fr.tse.fise3.poc.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import fr.tse.fise3.poc.domain.Time;
import fr.tse.fise3.poc.dto.TimeRequest;

public interface TimeService {
  
	
	public Collection<Time> findAllTimes();
	
	
	public Optional<Time> findTimesById(Long id);
	
	
	public Time createTime(TimeRequest timeRequest);
	
	
	public List<Time> getTimeContent(Long userId,String date);
	

	public Collection<Time> findTimesOfUser(Long idUser);  	


	public boolean deleteTime(Long timeId);

	
	
}
