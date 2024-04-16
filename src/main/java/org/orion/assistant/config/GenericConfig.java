package org.orion.assistant.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenericConfig {
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
