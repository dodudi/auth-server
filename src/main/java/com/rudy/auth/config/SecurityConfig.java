package com.rudy.auth.config;

import com.rudy.auth.exception.CustomAccessDeniedHandler;
import com.rudy.auth.exception.CustomAuthenticationEntryPoint;
import com.rudy.auth.filter.JwtAuthenticationFilter;
import com.rudy.auth.jwt.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(request -> request
                .requestMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/users/{userId}/roles").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/jwt/validate").permitAll()
                .requestMatchers(HttpMethod.POST, "/jwt/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs", "/v3/api-docs/swagger-config").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/auth/v3/api-docs/**", "/api/auth/swagger-ui/**", "/api/auth/swagger-ui.html").permitAll()
                .anyRequest().authenticated());

        http.exceptionHandling(ex -> ex
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(customAuthenticationEntryPoint)
        );

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtProvider, userDetailsService);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
