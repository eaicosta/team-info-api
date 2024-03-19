package com.devdavicosta.teaminfoapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI customAPI() {
		return new OpenAPI().info(new Info().title("Team Info API").description("API para criação de times de futebol.")
				.contact(new Contact().name("Davi Costa").email("davicostadelira04@gmail.com")).version("1.0.0"));
	}
}