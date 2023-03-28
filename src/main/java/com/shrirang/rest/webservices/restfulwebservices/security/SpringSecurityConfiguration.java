package com.shrirang.rest.webservices.restfulwebservices.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
		http.httpBasic(withDefaults());
		http.csrf().disable(); //for post and put method
		return http.build();
	}
}
