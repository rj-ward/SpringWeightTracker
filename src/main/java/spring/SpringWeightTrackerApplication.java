package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import spring.dao.WeightRepository;


@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = WeightRepository.class)
public class SpringWeightTrackerApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringWeightTrackerApplication.class, args);
	}

}
