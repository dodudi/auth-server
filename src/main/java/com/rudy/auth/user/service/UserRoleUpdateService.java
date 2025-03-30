package com.rudy.auth.user.service;

import com.rudy.auth.user.domain.RoleInfo;
import com.rudy.auth.user.domain.RoleType;
import com.rudy.auth.user.domain.UserInfo;
import com.rudy.auth.user.domain.UserRole;
import com.rudy.auth.user.repository.RoleInfoRepository;
import com.rudy.auth.user.repository.UserInfoRepository;
import com.rudy.auth.user.repository.UserRoleRepository;
import com.rudy.auth.user.request.UserRoleUpdateRequest;
import com.rudy.auth.user.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleUpdateService {
    private final UserRoleRepository userRoleRepository;
    private final UserInfoRepository userInfoRepository;
    private final RoleInfoRepository roleInfoRepository;

    @Transactional
    public UserInfoResponse updateRoles(Integer userId, UserRoleUpdateRequest request) {
        UserInfo userInfo = userInfoRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        List<UserRole> userRoles = userInfo.getUserRoles();

        List<String> roleTypes = request.getRoleTypes().stream()
                .map(Enum::name)
                .collect(Collectors.toCollection(ArrayList::new));

        for (UserRole userRole : userRoles) {
            if (!roleTypes.contains(userRole.getRoleInfo().getRoleName())) {
                userRoleRepository.delete(userRole);
                roleTypes.remove(userRole.getRoleInfo().getRoleName());
            }
        }

        for (String roleType : roleTypes) {
            RoleInfo roleInfo = roleInfoRepository.findByRoleName(roleType).orElseThrow(() -> new IllegalArgumentException("role not found"));
            UserRole userRole = new UserRole(userInfo, roleInfo);
            userRoleRepository.save(userRole);
        }

        return new UserInfoResponse(userInfo);
    }
}
