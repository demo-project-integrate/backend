package com.ims.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }
//    @Bean
//    public String keycloakIssuerUri() {
//        Dotenv dotenv = Dotenv.load();
//        return dotenv.get("KEYCLOAK_ISSUER_URI", "http://20.244.102.185:9000/realms/ims");
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        String issuerUri = System.getProperty("KEYCLOAK_ISSUER_URI", "http://localhost:9000/realms/ims");
//        return NimbusJwtDecoder.withJwkSetUri(issuerUri + "/protocol/openid-connect/certs").build();
//    }

}

