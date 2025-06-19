package com.rudy.auth.global.response;

import lombok.Getter;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@Getter
public class RSAKeyResponse {
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public RSAKeyResponse(PrivateKey privateKey, PublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String convertPrivateKeyToString() {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    public String convertPublicKeyToString() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
}
