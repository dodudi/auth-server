package com.rudy.auth.user.service;

import com.rudy.auth.user.domain.UserInfo;
import com.rudy.auth.user.repository.UserRepository;
import com.rudy.auth.user.request.UserRegisterRequest;
import com.rudy.auth.user.response.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserRegisterResponse register(UserRegisterRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        String encodePassword = passwordEncoder.encode(password);
        UserInfo user = new UserInfo(username, encodePassword);
        userRepository.save(user);

        return new UserRegisterResponse(user.getUsername(), user.getCreateDateTime(), user.getUpdateDateTime());
    }
}
