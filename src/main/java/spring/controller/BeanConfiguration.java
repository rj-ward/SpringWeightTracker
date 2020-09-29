package spring.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.model.User;
import spring.model.WeightEntry;

@Configuration
public class BeanConfiguration {

	@Bean
	public User user() {
		User bean = new User();
		return bean;
	}
	
	@Bean
	public WeightEntry weightEntry() {
		WeightEntry bean = new WeightEntry();
		return bean;
	}
	
	
}
