package fr.tse.fise3.poc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import fr.tse.fise3.poc.domain.Project;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.repository.ProjectRepository;
import fr.tse.fise3.poc.service.AuthService;
import fr.tse.fise3.poc.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private AuthService authService;
	
	@Autowired	
	private ProjectRepository projectRepository;
	
	
	// Find all projects on database
	@Transactional(readOnly = true)
	public Collection<Project> findAllProjects() {
		
		return this.projectRepository.findAll();
	}
	
	
	// Only a manager can create projects. The projects created by a manager are added to his list of projects
	@Transactional
	public Project createProject(Project project,String username) {
		
		User currentUser = this.authService.getLoggedInUserInfo(username);
				
		project.setManager(currentUser);
		
		return this.projectRepository.save(project);
	}
	
	@Transactional(readOnly = true)
	public List<Project>  findProjectsOfManager(Long managerId) {
		
		return this.projectRepository.findByManagerUserId(managerId);
	}
}
