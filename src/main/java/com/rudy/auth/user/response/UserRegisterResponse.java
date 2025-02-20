package com.rudy.auth.user.response;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UserRegisterResponse {

    private final String username;
    private final List<String> roleNames;
    private final LocalDateTime createDateTime;
    private final LocalDateTime updateDateTime;

    public UserRegisterResponse(String username, List<String> roleNames, LocalDateTime createDateTime, LocalDateTime updateDateTime) {
        this.username = username;
        this.roleNames = roleNames;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
    }
}
