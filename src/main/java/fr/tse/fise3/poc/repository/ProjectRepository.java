/**
 * 
 */
package fr.tse.fise3.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tse.fise3.poc.domain.Project;


public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	List<Project> findByManagerUserId(Long managerId);

}
