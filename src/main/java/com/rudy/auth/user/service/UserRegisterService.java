package com.rudy.auth.user.service;

import com.rudy.auth.user.domain.RoleInfo;
import com.rudy.auth.user.domain.UserInfo;
import com.rudy.auth.user.repository.RoleInfoRepository;
import com.rudy.auth.user.repository.UserInfoRepository;
import com.rudy.auth.user.request.UserRegisterRequest;
import com.rudy.auth.user.response.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRegisterService {
    private final UserInfoRepository userInfoRepository;
    private final RoleInfoRepository roleInfoRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserRegisterResponse register(UserRegisterRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        List<RoleInfo> roleInfos = request.getRoleNames().stream()
//                .filter(s -> !s.equals("admin"))
                .map(s -> roleInfoRepository
                        .findByRoleName(s)
                        .orElseGet(() -> roleInfoRepository.save(new RoleInfo(s))))
                .toList();

        UserInfo userInfo = userInfoRepository.findByUsername(username)
                .orElseGet(() -> new UserInfo(username, passwordEncoder.encode(password)));

        for (RoleInfo roleInfo : roleInfos) {
            userInfo.addUserRole(roleInfo);
        }

        userInfoRepository.save(userInfo);
        return new UserRegisterResponse(
                userInfo.getUsername(),
                userInfo.getUserRoles().stream().map(userRole -> userRole.getRoleInfo().getRoleName()).toList(),
                userInfo.getCreateDateTime(),
                userInfo.getUpdateDateTime()
        );
    }
}
