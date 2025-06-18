package com.rudy.auth.global.util;

import com.rudy.auth.global.response.RSAKeyResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.security.NoSuchAlgorithmException;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class RSAKeyGeneratorTest {

    private final RSAKeyGenerator rsaKeyGenerator = new RSAKeyGenerator();

    @Test
    @DisplayName(value = "RSA KEY 생성 테스트")
    void generate() {
        try {
            RSAKeyResponse key = rsaKeyGenerator.generate();
            log.info("private key: {}", key.getPrivateKey());
            log.info("public key: {}", key.getPublicKey());
        } catch (NoSuchAlgorithmException e) {
            log.error("error: {}", e.getMessage(), e);
        }
    }
}