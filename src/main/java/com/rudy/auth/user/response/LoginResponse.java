package com.rudy.auth.user.response;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final String accessToken;
    private final String refreshToken;
    private final long expiresIn;

    public LoginResponse(String accessToken, String refreshToken, long expiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }
}
