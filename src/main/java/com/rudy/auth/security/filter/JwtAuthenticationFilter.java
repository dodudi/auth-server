package com.rudy.auth.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudy.auth.security.JwtProvider;
import com.rudy.auth.security.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtTokenProvider;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // 요청의 JSON 데이터를 UserCredential 클래스로 변환
            LoginRequest credentials = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

            // 인증 토큰 생성
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());

            // 인증 시도
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException("Invalid authentication request");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 인증 성공 시 JWT 생성
        String token = jwtTokenProvider.generateToken(authResult);
        response.addHeader("Authorization", "Bearer " + token);

        // Optional: 응답에 추가 데이터 작성
        Map<String, String> tokenResponse = new HashMap<>();
        tokenResponse.put("accessToken", token);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), tokenResponse);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Authentication failed");
    }
}
