/**
 * 
 */
package fr.tse.fise3.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.tse.fise3.poc.domain.Time;


public interface TimeRepository extends JpaRepository<Time, Long> {

	public List<Time> findByUserUserId(Long managerId);

}
