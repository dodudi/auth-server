package com.rudy.auth.user.service;

import com.rudy.auth.user.domain.RoleInfo;
import com.rudy.auth.user.domain.RoleType;
import com.rudy.auth.user.domain.UserInfo;
import com.rudy.auth.user.domain.UserRole;
import com.rudy.auth.user.repository.RoleInfoRepository;
import com.rudy.auth.user.repository.UserInfoRepository;
import com.rudy.auth.user.repository.UserRoleRepository;
import com.rudy.auth.user.request.UserRegisterRequest;
import com.rudy.auth.user.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRegisterService {
    private final UserInfoRepository userInfoRepository;
    private final RoleInfoRepository roleInfoRepository;
    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserInfoResponse register(UserRegisterRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        RoleInfo roleInfo = roleInfoRepository.findByRoleName(RoleType.USER.name())
                .orElseThrow(() -> new IllegalArgumentException("role not found"));

        UserInfo userInfo = userInfoRepository.findByUsername(username)
                .orElseGet(() -> new UserInfo(username, passwordEncoder.encode(password)));
        userInfoRepository.save(userInfo);

        if (userInfo.getUserRoles().isEmpty()) {
            UserRole userRole = new UserRole(userInfo, roleInfo);
            userRoleRepository.save(userRole);
        }

        return new UserInfoResponse(
                userInfo.getUsername(),
                userInfo.getUserRoles().stream().map(role -> role.getRoleInfo().getRoleName()).toList(),
                userInfo.getCreateDateTime(),
                userInfo.getUpdateDateTime()
        );
    }
}
