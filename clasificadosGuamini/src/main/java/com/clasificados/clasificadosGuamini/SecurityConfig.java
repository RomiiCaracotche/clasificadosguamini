package com.clasificados.clasificadosGuamini;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* Permite peticiones a la ruta /usuarios sin estar autenticado */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF para pruebas en Postman
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/usuarios/**").permitAll() // Permite acceso libre a este endpoint
                .anyRequest().authenticated() // El resto de peticiones sí requieren autenticación
            );
        
        return http.build();
    }
}
