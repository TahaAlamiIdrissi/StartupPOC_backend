/**
 * 
 */
package fr.tse.fise3.poc.repository;

import java.time.Month;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


import fr.tse.fise3.poc.domain.Time;


public interface TimeRepository extends JpaRepository<Time, Long> {


	Collection<Time> findAllByUserUserIdAndCurrentMonth(Long userId,Month now);

	public List<Time> findByUserUserId(Long managerId);
}
