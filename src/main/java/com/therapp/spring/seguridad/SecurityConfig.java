package com.therapp.spring.seguridad;

import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
<<<<<<< HEAD
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para permitir peticiones desde Postman y PowerShell
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // Permitir carga de H2 Console en frames
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll() // Permitir acceso a H2 Console
                .requestMatchers("/api/**").authenticated() // Proteger rutas de la API
                .anyRequest().permitAll() // Permitir acceso al resto
=======
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers(
                        "/h2-console/**",
                        "/swagger-ui/**",
                        "/v3/**",
                        "/api/**"
                        ).permitAll() // Permitir acceso a la consola H2
                    .anyRequest().authenticated()
>>>>>>> a77ffc3 (implementacion de los DTO de terapeuta y usuario)
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Evitar sesiones
            .httpBasic(); // Habilitar autenticación básica para probar con Invoke-RestMethod

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin123")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}