package com.rudy.auth.config;

import com.rudy.auth.exception.CustomAccessDeniedHandler;
import com.rudy.auth.exception.CustomAuthenticationEntryPoint;
import com.rudy.auth.jwt.provider.JwtProvider;
import com.rudy.auth.filter.JwtAuthenticationFilter;
import com.rudy.auth.filter.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
    public SecurityFilterChain configure(HttpSecurity http, AuthenticationConfiguration authConfig) throws Exception {
        AuthenticationManager authenticationManager = authConfig.getAuthenticationManager();

        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(request -> request
//                .requestMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN")
                .requestMatchers("/jwt/validate", "/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs", "/v3/api-docs/swagger-config").permitAll()
                .requestMatchers("/api/auth/v3/api-docs/**", "/api/auth/swagger-ui/**", "/api/auth/swagger-ui.html").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated());

        http.exceptionHandling(ex -> ex
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(customAuthenticationEntryPoint)
        );

        LoginFilter loginFilter = new LoginFilter(authenticationManager, jwtProvider);
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtProvider, userDetailsService);
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter, LoginFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
