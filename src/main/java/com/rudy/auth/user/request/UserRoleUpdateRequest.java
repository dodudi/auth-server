package com.rudy.auth.user.request;

import com.rudy.auth.user.domain.RoleType;
import lombok.Getter;

import java.util.List;

@Getter
public class UserRoleUpdateRequest {
    private final List<RoleType> roleTypes;

    public UserRoleUpdateRequest(List<RoleType> roleTypes) {
        this.roleTypes = roleTypes;
    }
}
