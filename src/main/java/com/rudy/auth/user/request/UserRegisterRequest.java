package com.rudy.auth.user.request;

import com.rudy.auth.user.domain.RoleType;
import lombok.Getter;

import java.util.List;

@Getter
public class UserRegisterRequest {
    private final String username;
    private final String password;
    private final List<RoleType> roleTypes;

    public UserRegisterRequest(String username, String password, List<RoleType> roleTypes) {
        this.username = username;
        this.password = password;
        this.roleTypes = roleTypes;
    }

    public List<String> getRoleNames() {
        return roleTypes.stream()
                .map(RoleType::getRoleName)
                .toList();
    }
}
