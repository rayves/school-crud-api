package com.rvo.schoolcrudapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.rvo.schoolcrudapi.constants.ApiEndpoints;
import com.rvo.schoolcrudapi.enums.Role;
import com.rvo.schoolcrudapi.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
     * injects CustomUserDetailsService (an implementation of UserDetailsService)
     * via constructor for use as a Bean. Not needed as CustomUserDetailsService is
     * annotated as a @Service component ready for dependency injection
     */
    // private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, ApiEndpoints.STUDENTS_BASE_URL +
                        "/**")
                .hasRole(Role.STUDENT.name())
                .requestMatchers(HttpMethod.POST, ApiEndpoints.STUDENTS_BASE_URL +
                        "/**")
                .hasRole(Role.TEACHER.name())
                .requestMatchers(HttpMethod.PATCH, ApiEndpoints.STUDENTS_BASE_URL +
                        "/**")
                .hasRole(Role.TEACHER.name())
                .requestMatchers(HttpMethod.PUT, ApiEndpoints.STUDENTS_BASE_URL +
                        "/**")
                .hasRole(Role.TEACHER.name())
                .requestMatchers(HttpMethod.DELETE, ApiEndpoints.STUDENTS_BASE_URL +
                        "/**")
                .hasRole(Role.ADMIN.name())
                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    /**
     * Will automatically scan for authentication related beans eg.
     * {@link CustomUserDetailsService} and use for retrieving user details during
     * authentication.
     * <p>
     * AuthenticationConfiguration scans for any defined AuthenticationProvider
     * beans and registers them with the AuthenticationManager.
     * <p>
     * If there’s a UserDetailsService but no AuthenticationProvider, Spring will
     * automatically create a DaoAuthenticationProvider that uses the
     * UserDetailsService. If multiple AuthenticationProviders are defined, the
     * AuthenticationManager will try them in order until one successfully
     * authenticates the user or all fail.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*
     * This cannot be used along with the CustomUserDetailsService constructor
     * instantiation as that creates 2 Beans that creates confusion that can lead to
     * infinite recursion (StackOverflow Exception)
     */
    // @Bean
    // public UserDetailsService userDetailsService() {
    // return customUserDetailsService;
    // }
}
