package com.rudy.auth.global.util;

import com.rudy.auth.global.response.RSAKeyResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.security.*;

@Slf4j
@Getter
public class RSAKeyGenerator {
    public RSAKeyResponse generate() throws NoSuchAlgorithmException {
        // 1. KeyPairGenerator 생성 (RSA 알고리즘)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

        // 2. 키 크기 설정 (2048 bits 추천)
        keyGen.initialize(2048);

        // 3. 키 쌍 생성
        KeyPair keyPair = keyGen.generateKeyPair();

        // 4. PrivateKey와 PublicKey 획득
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        return new RSAKeyResponse(privateKey, publicKey);
    }
}
