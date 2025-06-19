package com.rudy.auth.client.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class ClientSearchRequest {
    @NotEmpty(message = "client id must not be empty")
    private final String clientId;

    @NotEmpty(message = "client secret must not be empty")
    private final String clientSecret;

    public ClientSearchRequest(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
