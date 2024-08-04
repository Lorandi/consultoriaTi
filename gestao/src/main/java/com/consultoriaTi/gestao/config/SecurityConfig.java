package com.consultoriaTi.gestao.config;

import com.consultoriaTi.gestao.infra.security.SecurityFilter;
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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                        .requestMatchers("/auth/register").hasRole("ADMIN")
                        .requestMatchers("/report").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/professional/**").hasAnyRole("ADMIN", "BUSINESS_PARTNER")
                        .requestMatchers(HttpMethod.PUT, "/professional/**").hasAnyRole("ADMIN", "BUSINESS_PARTNER")
                        .requestMatchers(HttpMethod.DELETE, "/professional/**").hasAnyRole("ADMIN", "BUSINESS_PARTNER")

                        .requestMatchers(HttpMethod.POST, "/allocation/**").hasAnyRole("ADMIN", "BUSINESS_PARTNER")
                        .requestMatchers(HttpMethod.PUT, "/allocation/**").hasAnyRole("ADMIN", "BUSINESS_PARTNER")
                        .requestMatchers(HttpMethod.DELETE, "/allocation/**").hasAnyRole("ADMIN", "BUSINESS_PARTNER")

                        .requestMatchers(HttpMethod.POST, "/deallocation/**").hasAnyRole("ADMIN", "BUSINESS_PARTNER")
                        .requestMatchers(HttpMethod.DELETE, "/deallocation/**").hasAnyRole("ADMIN", "BUSINESS_PARTNER")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
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
