// package com.rvo.schoolcrudapi.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.Customizer;
// import
// org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import
// org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// import com.rvo.schoolcrudapi.constants.ApiEndpoints;
// import com.rvo.schoolcrudapi.enums.Role;
// import com.rvo.schoolcrudapi.service.CustomUserDetailsService;

// import lombok.AllArgsConstructor;

// @Configuration
// @EnableWebSecurity
// @AllArgsConstructor
// public class SecurityConfig {

// private final CustomUserDetailsService customUserDetailsService;

// @Bean
// public BCryptPasswordEncoder passwordEncoder() {
// return new BCryptPasswordEncoder();
// }

// @Bean
// public SecurityFilterChain securityfilterChain(HttpSecurity http) throws
// Exception {
// http.authorizeHttpRequests(auth -> auth
// .requestMatchers(HttpMethod.GET, ApiEndpoints.STUDENTS_BASE_URL +
// "/**").hasAuthority(Role.STUDENT.getRole())
// .requestMatchers(HttpMethod.POST, ApiEndpoints.STUDENTS_BASE_URL +
// "/**").hasAuthority(Role.TEACHER.getRole())
// .requestMatchers(HttpMethod.PATCH, ApiEndpoints.STUDENTS_BASE_URL +
// "/**").hasAuthority(Role.TEACHER.getRole())
// .requestMatchers(HttpMethod.DELETE, ApiEndpoints.STUDENTS_BASE_URL +
// "/**").hasAuthority(Role.ADMIN.getRole())
// .anyRequest().authenticated())
// .formLogin(Customizer.withDefaults())
// .httpBasic(Customizer.withDefaults())
// .csrf(AbstractHttpConfigurer::disable);

// return http.build();
// }

// @Bean
// public AuthenticationManager
// authenticationManager(AuthenticationConfiguration
// authenticationConfiguration)
// throws Exception {
// return authenticationConfiguration.getAuthenticationManager();
// }

// @Bean
// public UserDetailsService userDetailsService() {
// return customUserDetailsService;
// }

// }
