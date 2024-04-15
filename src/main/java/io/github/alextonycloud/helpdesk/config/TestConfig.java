package io.github.alextonycloud.helpdesk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.github.alextonycloud.helpdesk.service.DBService;

@Configuration
@Profile(value = "test")
public class TestConfig {
	
	private final DBService dbService;

	public TestConfig(DBService dbService) {
		this.dbService = dbService;
	}
	
	@Bean
	public void instanciaDB() {
		this.dbService.instanciaDB();
		
	}
}
