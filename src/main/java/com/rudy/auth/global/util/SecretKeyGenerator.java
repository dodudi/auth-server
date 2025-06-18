package com.rudy.auth.global.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class SecretKeyGenerator {
    /**
     * Base64 인코딩 한 키를 발급받습니다. <br>
     *
     * {@code ex) client secret key}
     * */
    public String generate() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[48];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
