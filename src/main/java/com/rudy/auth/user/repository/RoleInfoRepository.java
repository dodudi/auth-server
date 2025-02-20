package com.rudy.auth.user.repository;

import com.rudy.auth.user.domain.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleInfoRepository extends JpaRepository<RoleInfo, Integer> {
    Optional<RoleInfo> findByRoleName(String roleName);
}
