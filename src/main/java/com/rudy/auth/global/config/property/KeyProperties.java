package com.rudy.auth.global.config.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Authentication Server Key Properties <br>
 * private key, public key is stored in application.yml
 */
@Getter
@Component
public class KeyProperties {
    @Value("${security.jwt.private-key}")
    private String privateKey;

    @Value("${security.jwt.public-key}")
    private String publicKey;
}
