package com.rudy.auth.jwt.controller;

import com.rudy.auth.jwt.request.LoginRequest;
import com.rudy.auth.jwt.response.JwtValidateResponse;
import com.rudy.auth.jwt.service.JwtService;
import com.rudy.auth.jwt.service.LoginService;
import com.rudy.auth.user.response.LoginResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jwt")
public class JwtController {
    private final JwtService jwtService;
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpServletResponse servletResponse) {
        LoginResponse response = loginService.login(request);
        servletResponse.setHeader("Authorization", "Bearer " + response.getAccessToken());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate")
    public ResponseEntity<JwtValidateResponse> validateJwt(@RequestParam(required = true, name = "token") String token) {
        JwtValidateResponse jwtValidateResponse = jwtService.validateToken(token);
        return ResponseEntity.ok(jwtValidateResponse);
    }
}
