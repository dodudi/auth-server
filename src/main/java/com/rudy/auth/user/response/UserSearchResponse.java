package com.rudy.auth.user.response;

import com.rudy.auth.user.domain.RoleType;
import com.rudy.auth.user.domain.UserInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class UserSearchResponse {
    private final String username;
    private final List<RoleType> roleType;

    public UserSearchResponse(UserInfo userInfo) {
        this.username = userInfo.getUsername();
        this.roleType = userInfo.getUserRoles().stream()
                .map(userRole -> userRole.getRoleInfo().getRoleName().toUpperCase())
                .map(RoleType::valueOf)
                .toList();
    }
}
