package com.rudy.auth.user.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserRegisterResponse {

    private final String username;
    private final LocalDateTime createDateTime;
    private final LocalDateTime updateDateTime;

    public UserRegisterResponse(String username, LocalDateTime createDateTime, LocalDateTime updateDateTime) {
        this.username = username;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
    }
}
