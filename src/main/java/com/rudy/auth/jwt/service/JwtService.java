package com.rudy.auth.jwt.service;

import com.rudy.auth.jwt.provider.JwtProvider;
import com.rudy.auth.jwt.response.JwtValidateResponse;
import com.rudy.auth.user.domain.UserInfo;
import com.rudy.auth.user.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtProvider jwtProvider;
    private final UserInfoRepository userInfoRepository;

    public JwtValidateResponse validateToken(String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7).trim();
            }

            boolean isValidated = jwtProvider.validateToken(token);
            String username = jwtProvider.getUsernameFromToken(token);

            UserInfo userInfo = userInfoRepository.findByFetchUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("user not found by token"));

            Integer userId = userInfo.getId();
            Set<String> userRoles = userInfo.getUserRoles().stream()
                    .map(userRole -> userRole.getRoleInfo().getRoleName())
                    .collect(Collectors.toSet());

            return new JwtValidateResponse(isValidated, userId, username, userRoles);
        } catch (Exception e) {
            return new JwtValidateResponse(false, -1, "", null);
        }
    }
}
