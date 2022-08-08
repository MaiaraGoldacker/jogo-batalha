package com.jogo.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@Component
@ActiveProfiles("test")
public class FlywayConfig {

	@Bean
	FlywayMigrationStrategy integrationMigrationStrategy() {
		FlywayMigrationStrategy strategy = flyway -> {
			flyway.clean();
			flyway.migrate();
		};
		return strategy;
	}
}
