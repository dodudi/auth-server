package com.rudy.auth.global.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.security.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
public class RSAKeyGenerator {
    public Map<String, Object> generate() throws NoSuchAlgorithmException {
        // 1. KeyPairGenerator 생성 (RSA 알고리즘)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

        // 2. 키 크기 설정 (2048 bits 추천)
        keyGen.initialize(2048);

        // 3. 키 쌍 생성
        KeyPair keyPair = keyGen.generateKeyPair();

        // 4. PrivateKey와 PublicKey 획득
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // 확인용 출력
        log.info("Private Key Format: {}", privateKey.getFormat());
        log.info("Public Key Format: {}", publicKey.getFormat());

        HashMap<String, Object> keys = new HashMap<>();
        keys.put("privateKey", privateKey);
        keys.put("publicKey", publicKey);
        return keys;
    }
}
