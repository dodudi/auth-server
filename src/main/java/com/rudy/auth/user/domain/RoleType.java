package com.rudy.auth.user.domain;

import lombok.Getter;

@Getter
public enum RoleType {
    USER("user"), ADMIN("admin"), GUEST("guest");

    private final String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }
}
