package com.br.joaoptgaino.jwtauthlearning.infra.security.config;


import com.br.joaoptgaino.jwtauthlearning.infra.security.filter.SecurityFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final SecurityFilter securityFilter;

    private static final String[] PUBLIC_POST_ENDPOINTS = {
            "/auth/login",
            "/auth/signUp",
    };

    private static final String[] ADMIN_POST_ENDPOINTS = {
            "/auth/register",
    };

    private static final String[] ADMIN_GET_ENDPOINTS = {
            "/users",
            "/users/{username}"
    };

    private static final String[] USER_GET_ENDPOINTS = {
            "/products",
            "/users/me"
    };

    private static final String[] USER_POST_ENDPOINTS = {
            "/products",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, USER_GET_ENDPOINTS).authenticated()
                        .requestMatchers(HttpMethod.POST, USER_POST_ENDPOINTS).authenticated()
                        .requestMatchers(HttpMethod.GET, ADMIN_GET_ENDPOINTS).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, ADMIN_POST_ENDPOINTS).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
