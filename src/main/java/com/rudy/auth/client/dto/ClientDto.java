package com.rudy.auth.client.dto;

import lombok.Getter;

@Getter
public class ClientDto {
    private final String clientId;
    private final String clientSecret;

    public ClientDto(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
