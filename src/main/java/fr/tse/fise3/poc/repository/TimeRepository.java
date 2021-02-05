/**
 * 
 */
package fr.tse.fise3.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tse.fise3.poc.domain.Time;

/**
 * @author root
 *
 */
public interface TimeRepository extends JpaRepository<Time, Long> {
	
	Time findByUserUserId(Long userId);
}
