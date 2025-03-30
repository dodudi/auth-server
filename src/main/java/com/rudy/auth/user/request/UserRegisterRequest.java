package com.rudy.auth.user.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class UserRegisterRequest {
    @NotEmpty(message = "username must not be empty")
    private final String username;

    @NotEmpty(message = "username must not be empty")
    private final String password;

    public UserRegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
