package io.github.alextonycloud.helpdesk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.github.alextonycloud.helpdesk.service.DBService;

@Configuration
@Profile(value = "dev")
public class DevConfig {
	
	private final DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;

	public DevConfig(DBService dbService) {
		this.dbService = dbService;
	}
	
	@Bean
	public boolean instanciaDB() {
		if(value.equals("create")) {
			this.dbService.instanciaDB();
		}
		return false;
	}
}
