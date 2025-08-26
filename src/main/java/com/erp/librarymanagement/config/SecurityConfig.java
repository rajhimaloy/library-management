package com.erp.librarymanagement.config;

import com.erp.librarymanagement.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * Author: Rajib Kumer Ghosh
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/**").permitAll() // Allow actuator access without auth
                        .requestMatchers("/public/**").permitAll()   // Example: any public endpoints
                        .requestMatchers("/book/**").permitAll()   // Example: any book endpoints
                        .requestMatchers("/borrower/**").permitAll()   // Example: any borrower endpoints
                        .requestMatchers("/loan/**").permitAll()   // Example: any loan endpoints
                        .anyRequest().authenticated()                         // Secure all other endpoints
                );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/actuator/**").permitAll() // Allow actuator without auth
                .anyRequest().authenticated()
                .and()
                .httpBasic(); // Basic auth if needed
        return http.build();
    }*/
}
