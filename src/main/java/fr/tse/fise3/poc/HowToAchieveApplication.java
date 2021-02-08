package fr.tse.fise3.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import fr.tse.fise3.poc.domain.Time;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync
@EnableSwagger2
public class HowToAchieveApplication {

	public static void main(String[] args) {
		SpringApplication.run(HowToAchieveApplication.class, args);
		
	}

}
