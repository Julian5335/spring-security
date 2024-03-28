package com.julian5335.spring.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    @Bean
    @Order(1)
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        http
			.securityMatcher("/api/admin/**")                              
			.authorizeHttpRequests(authorize -> authorize
				.anyRequest().hasRole("ADMIN")
			)
			.httpBasic();
        http.userDetailsService(adminService());
		return http.build();
    }

    @Bean
    public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
        http
			.securityMatcher("/api/user/**")                              
			.authorizeHttpRequests(authorize -> authorize
				.anyRequest().hasRole("USER")
			)
			.httpBasic();
        http.userDetailsService(userService());
		return http.build();
    }

    @Bean
    public AdminService adminService() throws Exception {
        return new AdminService();
    }

    @Bean
    public UserService userService() throws Exception {
        return new UserService();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
