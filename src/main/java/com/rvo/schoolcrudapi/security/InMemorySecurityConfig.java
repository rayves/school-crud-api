package com.rvo.schoolcrudapi.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.rvo.schoolcrudapi.enums.Role;

@Configuration
public class InMemorySecurityConfig {

    private static final String ENDPOINT_STUDENTS = "/students";

    // Users to be added to the add in memory
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails john = createUsers("john", "test123", Role.TEACHER);
        UserDetails mary = createUsers("mary", "test123", Role.TEACHER, Role.STUDENT);
        UserDetails susan = createUsers("susan", "test123", Role.TEACHER, Role.STUDENT, Role.ADMIN);

        return new InMemoryUserDetailsManager(john, mary, susan);
    }

    private UserDetails createUsers(String user, String password, Role... roles) {
        return User.withUsername(user)
                .password("{noop}" + password)
                .roles(
                        Arrays.stream(roles)
                                .map(Role::name)
                                .toArray(String[]::new))
                .build();
    }

    // Endpoint authorization for users
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers(HttpMethod.GET, ENDPOINT_STUDENTS).hasRole(Role.TEACHER.name())
                .requestMatchers(HttpMethod.GET, ENDPOINT_STUDENTS + "/**").hasRole(Role.TEACHER.name())
                .requestMatchers(HttpMethod.POST, ENDPOINT_STUDENTS).hasRole(Role.STUDENT.name())
                .requestMatchers(HttpMethod.PUT, ENDPOINT_STUDENTS + "/**")
                .hasRole(Role.STUDENT.name())
                .requestMatchers(HttpMethod.DELETE, ENDPOINT_STUDENTS + "/**")
                .hasRole(Role.ADMIN.name()));

        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}