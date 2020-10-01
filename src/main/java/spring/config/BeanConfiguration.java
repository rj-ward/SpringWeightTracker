package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.model.WeightEntry;

/**
 * This class holds configuration of beans for Spring Framework use.
 * 
 * @author Remy Ward
 * @version 1.0
 * @since 2020-10-1
 */
@Configuration
public class BeanConfiguration {

	@Bean
	public WeightEntry weightEntry() {
		WeightEntry bean = new WeightEntry();
		return bean;
	}

}
