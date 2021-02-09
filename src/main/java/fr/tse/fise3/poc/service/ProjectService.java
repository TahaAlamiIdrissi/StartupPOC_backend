package fr.tse.fise3.poc.service;

import java.util.Collection;
import java.util.List;

import fr.tse.fise3.poc.domain.Project;

public interface ProjectService {

	public Collection<Project> findAllProjects();
	
	public Project createProject(Project project,String username);
	
	public List<Project>  findProjectsOfManager(Long managerId);
}
