package fr.tse.fise3.poc.project;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import fr.tse.fise3.poc.domain.Project;
import fr.tse.fise3.poc.service.ProjectService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
public class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;
    
	@Test
	public void testFindAllProjects() {

		Collection<Project> tasks = this.projectService.findAllProjects();
		
		Assert.assertEquals(1, tasks.size());
	}

}
