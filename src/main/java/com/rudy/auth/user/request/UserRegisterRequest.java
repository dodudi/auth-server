package com.rudy.auth.user.request;

import lombok.Getter;

@Getter
public class UserRegisterRequest {
    private final String username;
    private final String password;

    public UserRegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
