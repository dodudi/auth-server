package com.rudy.auth.user.repository;

import com.rudy.auth.user.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    @Query("SELECT ui FROM UserInfo ui JOIN FETCH ui.userRoles ur JOIN FETCH ur.roleInfo  WHERE ui.username = :username")
    Optional<UserInfo> findByFetchUsername(String username);

    Optional<UserInfo> findByUsername(String username);

    boolean existsByUsername(String username);
}
