package com.example.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().components(new Components()).info(apiInfo());
	}
	
	private Info apiInfo() {
		return new Info()
				.title("연습용 API 문서")
				.description("Swagger로 API 만드는 법 연습중")
				.license(new License())
				.version("0.0.1");
	}
}
