package com.example.test.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
	    security
	        .cors(cors -> cors.disable())
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(authorize -> authorize
	            .anyRequest().permitAll()
	            
	        );
	    return security.build();
	};
}
