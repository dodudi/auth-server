package com.rudy.auth.global.config.property;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Authentication Server Key Properties <br>
 * private key, public key is stored in application.yml
 */
@Getter
@Component
@ConfigurationProperties(prefix = "security.jwt")
public class KeyProperties {
    private String privateKey;
    private String publicKey;
}
