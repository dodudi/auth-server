package com.rudy.auth.jwt.service;

import com.rudy.auth.jwt.provider.JwtProvider;
import com.rudy.auth.jwt.request.LoginRequest;
import com.rudy.auth.user.domain.UserInfo;
import com.rudy.auth.user.repository.UserInfoRepository;
import com.rudy.auth.user.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final PasswordEncoder passwordEncoder;
    private final UserInfoRepository userInfoRepository;
    private final JwtProvider jwtProvider;

    public LoginResponse login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

        UserInfo userInfo = userInfoRepository.findByFetchUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        if (!passwordEncoder.matches(password, userInfo.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        String accessToken = jwtProvider.generateAccessToken(username);
        String refreshToken = jwtProvider.generateRefreshToken(username);
        long accessTokenExpiration = jwtProvider.getAccessTokenExpiration();
        
        return new LoginResponse(accessToken, refreshToken, accessTokenExpiration);
    }
}
