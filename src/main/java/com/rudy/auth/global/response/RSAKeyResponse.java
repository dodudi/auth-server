package com.rudy.auth.global.response;

import lombok.Getter;

import java.security.PrivateKey;
import java.security.PublicKey;

@Getter
public class RSAKeyResponse {
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public RSAKeyResponse(PrivateKey privateKey, PublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
}
