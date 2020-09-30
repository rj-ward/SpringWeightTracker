package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import spring.model.WeightEntry;

@Configuration
public class BeanConfiguration {

	
	@Bean
	public WeightEntry weightEntry() {
		WeightEntry bean = new WeightEntry();
		return bean;
	}
	


	
}
