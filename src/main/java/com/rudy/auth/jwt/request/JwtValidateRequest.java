package com.rudy.auth.jwt.request;

import lombok.Getter;

@Getter
public class JwtValidateRequest {
    private final String token;

    public JwtValidateRequest(String token) {
        this.token = token;
    }
}
