package com.rudy.auth.jwt.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotEmpty(message = "username must not be empty")
    private final String username;

    @NotEmpty(message = "password must not be empty")
    private final String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
