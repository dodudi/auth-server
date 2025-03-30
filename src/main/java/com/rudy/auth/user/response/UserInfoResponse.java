package com.rudy.auth.user.response;

import com.rudy.auth.user.domain.UserInfo;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UserInfoResponse {

    private final String username;
    private final List<String> roleNames;
    private final LocalDateTime createDateTime;
    private final LocalDateTime updateDateTime;

    public UserInfoResponse(UserInfo userInfo) {
        this.username = userInfo.getUsername();
        this.roleNames = userInfo.getUserRoles().stream()
                .map(userRole -> userRole.getRoleInfo().getRoleName())
                .toList();
        this.createDateTime = userInfo.getCreateDateTime();
        this.updateDateTime = userInfo.getUpdateDateTime();
    }

    public UserInfoResponse(String username, List<String> roleNames, LocalDateTime createDateTime, LocalDateTime updateDateTime) {
        this.username = username;
        this.roleNames = roleNames;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
    }
}
