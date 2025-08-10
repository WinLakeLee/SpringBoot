package com.example.member.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig {

//	private static final String[] PUBLIC_URLS = {
//			"/"
//			, "images/**"
//			, "/css/**"
//			, "/js/**"
//			, "/login
//	};
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
//	    HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
//	    requestCache.setMatchingRequestParameterName("UserId");
//	    
	    security
	        .cors(cors -> cors.disable())
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(authorize -> authorize
//	            //.requestMatchers(PUBLIC_URLS).permitAll()
	            .anyRequest().permitAll()
//	        )
//	        .httpBasic(Customizer.withDefaults())
//	        .formLogin(formLogin -> formLogin
//	            .loginPage("/login")
//	            .failureHandler((request, response, exception) -> {
//	                response.sendRedirect("/login?error");
//	            })
//	            .permitAll()
//	        )
//	        .logout(logout -> logout
////	            .logoutUrl("/")
//	            .logoutSuccessUrl("/")
//	        )
//	        .requestCache(requestCacheCustomizer -> requestCacheCustomizer
//	            .requestCache(requestCache)
	        );

	    return security.build();
	}
}
