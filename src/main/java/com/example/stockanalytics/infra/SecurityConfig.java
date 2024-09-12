package com.example.stockanalytics.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/user/create").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/product/create").authenticated()
                        .requestMatchers(HttpMethod.POST, "/product/update/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/product/list").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/product/delete/**").authenticated()
                        .requestMatchers(HttpMethod.GET,"product/check-stock/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/product/listName/").authenticated()
                        .requestMatchers(HttpMethod.GET, "/product/listPrice/").authenticated()
                        .requestMatchers(HttpMethod.GET, "/product/listQuantity/").authenticated()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers
                        .defaultsDisabled()
                        .cacheControl(cache -> cache.disable())
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
