// package com.rvo.schoolcrudapi.security;

// import java.util.Arrays;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.Customizer;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;

// import com.rvo.schoolcrudapi.constants.ApiEndpoints;
// import com.rvo.schoolcrudapi.enums.Role;

// @Configuration
// public class InMemorySecurityConfig {

// // Users to be added in memory
// @Bean
// public InMemoryUserDetailsManager userDetailsManager() {

// UserDetails john = createUsers("student", "student123", Role.STUDENT);
// UserDetails mary = createUsers("teacher", "teacher123", Role.TEACHER,
// Role.STUDENT);
// UserDetails susan = createUsers("admin", "admin123", Role.TEACHER,
// Role.STUDENT, Role.ADMIN);

// return new InMemoryUserDetailsManager(john, mary, susan);
// }

// private UserDetails createUsers(String user, String password, Role... roles)
// {
// return User.withUsername(user)
// .password("{noop}" + password)
// .roles(
// Arrays.stream(roles)
// .map(Role::name)
// .toArray(String[]::new))
// .build();
// // Automatically prefixes roles with "ROLE_"
// }

// // // This only works with a Simple users and authorities one to many
// // // relationship
// // @Bean
// // public UserDetailsManager userDetailsManager(DataSource datasource) {

// // return new JdbcUserDetailsManager(datasource);

// // }

// // Endpoint authorization for roles
// @Bean
// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// http.authorizeHttpRequests(configurer -> configurer
// .requestMatchers(HttpMethod.GET, ApiEndpoints.STUDENTS_BASE_URL +
// "/**")
// .hasRole(Role.STUDENT.name())
// .requestMatchers(HttpMethod.POST,
// ApiEndpoints.STUDENTS_BASE_URL)
// .hasRole(Role.TEACHER.name())
// .requestMatchers(HttpMethod.PUT, ApiEndpoints.STUDENTS_BASE_URL +
// "/**")
// .hasRole(Role.TEACHER.name())
// .requestMatchers(HttpMethod.DELETE, ApiEndpoints.STUDENTS_BASE_URL +
// "/**")
// .hasRole(Role.ADMIN.name()));

// // use HTTP Basic authentication
// http.httpBasic(Customizer.withDefaults());

// // disable Cross Site Request Forgery (CSRF)
// http.csrf(csrf -> csrf.disable());

// return http.build();
// }
// }