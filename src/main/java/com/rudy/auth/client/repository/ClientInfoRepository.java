package com.rudy.auth.client.repository;

import com.rudy.auth.client.domain.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientInfoRepository extends JpaRepository<ClientInfo, Integer> {
    Optional<ClientInfo> findByClientId(String clientId);
}
