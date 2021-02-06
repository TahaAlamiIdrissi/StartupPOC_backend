/**
 * 
 */
package fr.tse.fise3.poc.repository;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import fr.tse.fise3.poc.domain.Time;


public interface TimeRepository extends JpaRepository<Time, Long> {


	Collection<Time> findAllByUserUserIdAndDateStart(Long userId,LocalDateTime now);

	public List<Time> findByUserUserId(Long managerId);
}
