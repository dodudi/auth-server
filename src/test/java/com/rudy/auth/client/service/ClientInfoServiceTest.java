package com.rudy.auth.client.service;

import com.rudy.auth.client.dto.ClientDto;
import com.rudy.auth.client.request.ClientRegisterRequest;
import com.rudy.auth.client.request.ClientSearchRequest;
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
        ClientRegisterRequest clientRegisterRequest = new ClientRegisterRequest("test_id", secretKeyGenerator.generate());
        String register = clientInfoService.register(clientRegisterRequest);
        log.info("register: {}", register);
    }

    @Test
    void search() {
        final String clientId = "test_id";
        final String clientSecret = secretKeyGenerator.generate();

        ClientRegisterRequest clientRegisterRequest = new ClientRegisterRequest(clientId, clientSecret);
        clientInfoService.register(clientRegisterRequest);

        ClientSearchRequest clientSearchRequest = new ClientSearchRequest(clientId, clientSecret);
        ClientDto search = clientInfoService.search(clientSearchRequest);
        log.info("clientId: {} clientSecret: {}", search.getClientId(), search.getClientSecret());
    }
}