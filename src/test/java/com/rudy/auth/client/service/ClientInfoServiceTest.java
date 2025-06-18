package com.rudy.auth.client.service;

import com.rudy.auth.client.request.ClientRegistRequest;
import com.rudy.auth.global.util.SecretKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class ClientInfoServiceTest {

    @Autowired
    private ClientInfoService clientInfoService;

    private final SecretKeyGenerator secretKeyGenerator = new SecretKeyGenerator();

    @Test
    void register() {
        ClientRegistRequest clientRegistRequest = new ClientRegistRequest("test_id", secretKeyGenerator.generate());
        String register = clientInfoService.register(clientRegistRequest);
        log.info("register: {}", register);
    }
}