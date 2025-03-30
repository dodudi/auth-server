package com.rudy.auth.user.service;

import com.rudy.auth.user.repository.UserInfoRepository;
import com.rudy.auth.user.response.UserInfoResponse;
import com.rudy.auth.user.response.UserSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSearchService {
    private final UserInfoRepository userInfoRepository;

    @Transactional(readOnly = true)
    public List<UserInfoResponse> findAll(Pageable pageable) {
        return userInfoRepository.findAll(pageable)
                .map(UserInfoResponse::new)
                .toList();
    }
}
