package com.rudy.auth.client.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ClientRegisterRequest {
    @NotNull(message = "client id must not be null")
    private final String clientId;

    @NotNull(message = "client secret must not be null")
    private final String clientSecret;

    public ClientRegisterRequest(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
