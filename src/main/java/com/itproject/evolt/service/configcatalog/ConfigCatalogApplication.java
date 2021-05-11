package com.itproject.evolt.service.configcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
public class ConfigCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigCatalogApplication.class, args);
	}

}
