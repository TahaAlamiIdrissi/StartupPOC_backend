package fr.tse.fise3.poc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tse.fise3.poc.domain.Project;
import fr.tse.fise3.poc.repository.ProjectRepository;
import fr.tse.fise3.poc.repository.RoleRepository;
import fr.tse.fise3.poc.repository.TimeRepository;
import fr.tse.fise3.poc.repository.UserRepository;
import fr.tse.fise3.poc.repository.VerificationTokenRepositoy;

@Service
public class ProjectService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;	
	
	@Autowired	
	private ProjectRepository projectRepository;
	
	@Autowired	
	private TimeRepository timeRepository;
	
	@Autowired	
	private VerificationTokenRepositoy verificationTokenRepository;
	
	@Transactional(readOnly = true)
	public Collection<Project> findAllProjects() {
		
		return this.projectRepository.findAll();
	}
	
	@Transactional
	public Project createProject(Project project) {

		return this.projectRepository.save(project);
	}
}
