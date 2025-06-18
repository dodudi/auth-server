package com.rudy.auth.global.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class SecretKeyGeneratorTest {

    private final SecretKeyGenerator secretKeyGenerator = new SecretKeyGenerator();

    @Test
    void generate() {
        String generate = secretKeyGenerator.generate();
        log.info("generate: {}", generate);
    }
}