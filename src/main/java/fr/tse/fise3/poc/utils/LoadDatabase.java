package fr.tse.fise3.poc.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import fr.tse.fise3.poc.domain.Project;
import fr.tse.fise3.poc.repository.ProjectRepository;
import fr.tse.fise3.poc.service.ProjectService;


@Configuration
public class LoadDatabase {

	@Bean
	@Profile("test")
	CommandLineRunner initTestData(ProjectRepository projectRepository, ProjectService projectService) {
		
		
		return new CommandLineRunner() {

			public void run(String... args) throws Exception {
                Project prj1 = new Project();
                prj1.setTitle("Project Start-up POC");
                prj1.setDescription("Fise 3 Springboot Angular project");
                projectRepository.save(prj1);

                
			}	
	};
	}
}
