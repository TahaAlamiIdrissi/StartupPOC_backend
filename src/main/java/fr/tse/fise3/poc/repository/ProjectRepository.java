/**
 * 
 */
package fr.tse.fise3.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tse.fise3.poc.domain.Project;

/**
 * @author root
 *
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
