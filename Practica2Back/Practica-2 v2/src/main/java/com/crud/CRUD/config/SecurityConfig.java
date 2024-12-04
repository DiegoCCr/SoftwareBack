package com.crud.CRUD.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF para pruebas (habilitar en producción)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/**").permitAll() // Permitir acceso sin autenticación a todos los endpoints
            )
            .cors(cors -> cors.disable()) // Desactiva CORS para pruebas (habilitar según sea necesario)
            .httpBasic(httpBasic -> httpBasic.disable()); // Desactiva la autenticación básica para acceso libre

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}