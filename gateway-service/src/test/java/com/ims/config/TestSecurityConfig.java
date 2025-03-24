package com.ims.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Profile("test")
@TestConfiguration
public class TestSecurityConfig {

    @Bean
    public SecurityFilterChain testsecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Disable authentication
                .csrf(csrf -> csrf.disable()); // Disable CSRF for tests
        return http.build();
    }
}

