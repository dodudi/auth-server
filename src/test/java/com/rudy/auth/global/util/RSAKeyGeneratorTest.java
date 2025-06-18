package com.rudy.auth.global.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class RSAKeyGeneratorTest {

    private final RSAKeyGenerator rsaKeyGenerator = new RSAKeyGenerator();

    @Test
    @DisplayName(value = "RSA KEY 생성 테스트")
    void generate() {
        try {
            Map<String, Object> generate = rsaKeyGenerator.generate();
            log.info("private key: {}", generate.get("privateKey"));
            log.info("public key: {}", generate.get("publicKey"));
        } catch (NoSuchAlgorithmException e) {
            log.error("error: {}", e.getMessage(), e);
        }
    }
}