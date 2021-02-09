/**
 * 
 */
package fr.tse.fise3.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


import fr.tse.fise3.poc.domain.Time;


public interface TimeRepository extends JpaRepository<Time, Long> {


	public List<Time> findAllByUserUserIdAndDateOfProject(Long userId,String date);

	public List<Time> findByUserUserId(Long managerId);
	
	
}
