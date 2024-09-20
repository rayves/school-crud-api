package com.luv2code.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class InMemorySecurityConfig {

        // Users to be added to the add in memory
        @Bean
        public InMemoryUserDetailsManager userDetailsManager() {

                UserDetails john = User.withUsername("john")
                                .password("{noop}test123")
                                .roles("TEACHER")
                                .build();

                UserDetails mary = User.withUsername("mary")
                                .password("{noop}test123")
                                .roles("TEACHER", "PRINCIPAL")
                                .build();

                UserDetails susan = User.withUsername("susan")
                                .password("{noop}test123")
                                .roles("TEACHER", "PRINCIPAL", "ADMIN")
                                .build();

                return new InMemoryUserDetailsManager(john, mary, susan);
        }

        // Endpoint authorization for users
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests(configurer -> configurer
                                .requestMatchers(HttpMethod.GET, "/students").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.GET, "/students/**").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.POST, "/students").hasRole("PRINCIPAL")
                                .requestMatchers(HttpMethod.PUT, "/students/**").hasRole("PRINCIPAL")
                                .requestMatchers(HttpMethod.DELETE, "/students/**").hasRole("ADMIN"));

                // use HTTP Basic authentication
                http.httpBasic(Customizer.withDefaults());

                // disable Cross Site Request Forgery (CSRF)
                http.csrf(csrf -> csrf.disable());

                return http.build();
        }
}