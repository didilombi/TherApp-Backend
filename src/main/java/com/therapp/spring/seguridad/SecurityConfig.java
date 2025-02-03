package com.therapp.spring.seguridad;

import org.springframework.security.core.userdetails.User;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers(
                        "/h2-console/**",
                        "/swagger-ui/**",
                        "/v3/**",
                        "/api/**"
                        ).permitAll() // Permitir acceso a la consola H2
                    .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para la consola H2
            .headers(headers -> headers.frameOptions().disable()); // Deshabilitar frame options para la consola H2

        return http.build();
    }
}