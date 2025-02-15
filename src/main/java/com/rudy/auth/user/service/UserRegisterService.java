package com.rudy.auth.user.service;

import com.rudy.auth.user.domain.UserInfo;
import com.rudy.auth.user.repository.UserRepository;
import com.rudy.auth.user.request.UserRegisterRequest;
import com.rudy.auth.user.response.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserRegisterResponse register(UserRegisterRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        UserInfo userInfo = userRepository.findByUsername(username)
                .orElseGet(() -> new UserInfo(username, passwordEncoder.encode(password)));

        userRepository.save(userInfo);
        return new UserRegisterResponse(userInfo.getUsername(), userInfo.getCreateDateTime(), userInfo.getUpdateDateTime());
    }
}
