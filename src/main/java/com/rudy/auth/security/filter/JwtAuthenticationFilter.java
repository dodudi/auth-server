package com.rudy.auth.security.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudy.auth.security.JwtProvider;
import com.rudy.auth.security.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String contentType = request.getContentType();

            if (contentType != null) {
                if (contentType.contains("application/json")) {
                    // JSON 데이터 처리
                    handleJsonRequest(request);
                } else if (contentType.contains("application/x-www-form-urlencoded")) {
                    // Form 데이터 처리
                    handleFormRequest(request);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String method = request.getMethod();
        String path = request.getRequestURI();
        return !path.equals("/login");
    }

    private void handleJsonRequest(HttpServletRequest request) throws IOException {
        String body = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        LoginRequest loginRequest = objectMapper.readValue(body, LoginRequest.class);
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

    }

    private void handleFormRequest(HttpServletRequest request) {
        // Form 데이터 읽기
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Username (Form): " + username);
        System.out.println("Password (Form): " + password);
    }
}
