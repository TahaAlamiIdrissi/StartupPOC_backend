package fr.tse.fise3.poc.utils;

import java.time.Instant;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.time.LocalDateTime;
import fr.tse.fise3.poc.domain.Project;
import fr.tse.fise3.poc.domain.Role;
import fr.tse.fise3.poc.domain.Time;
import fr.tse.fise3.poc.domain.User;
import fr.tse.fise3.poc.repository.ProjectRepository;
import fr.tse.fise3.poc.repository.RoleRepository;
import fr.tse.fise3.poc.repository.TimeRepository;
import fr.tse.fise3.poc.repository.UserRepository;
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
	
	@Bean
	CommandLineRunner initData(ProjectRepository projectRepository, RoleRepository roleRepository,
			UserRepository userRepository, TimeRepository timeRepository) {
		return new CommandLineRunner() {

			public void run(String... args) throws Exception {
               initRoleTable(roleRepository);
               initUserTable(userRepository);
               initProjectTable(projectRepository);
               initTimeTable(timeRepository);
			}	
		};
	}
	
	private void initProjectTable(ProjectRepository projectRepository) {
		Project prj1 = new Project();
        prj1.setTitle("Project Start-up POC");
        prj1.setDescription("Fise 3 Springboot Angular project");
        projectRepository.save(prj1);  
	}
	
	private void initTimeTable(TimeRepository timeRepository) {
		Time time = new Time();
		time.setDate_start(LocalDateTime.now());
		time.setDate_end(LocalDateTime.now());
	}
	
	private void initUserTable(UserRepository userRepository) {
		User user = new User();
		user.setUsername("usernameOne");
		user.setPassword("1234567");
		user.setEmail("username@gmail.com");
		user.setFirstname("firstname");
		user.setLastname("lastname");
		user.setCreatedAt(Instant.now());
		userRepository.save(user);
	}
	
	private void initRoleTable(RoleRepository roleRepository) {
		Role employee = new Role();
		employee.setId(RoleUtils.EMPLOYEE_ID);
		employee.setLabel(RoleUtils.EMPLOYEE_LABEL);
		roleRepository.save(employee);
		
		Role manager = new Role();
		manager.setId(RoleUtils.MANAGER_ID);
		manager.setLabel(RoleUtils.MANAGER_LABEL);
		roleRepository.save(manager);
		
		
		Role admin = new Role();
		admin.setId(RoleUtils.ADMIN_ID);
		admin.setLabel(RoleUtils.ADMIN_LABEL);
		roleRepository.save(admin);
		
	}
	
}
