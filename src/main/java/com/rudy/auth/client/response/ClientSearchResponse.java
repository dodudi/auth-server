package com.rudy.auth.client.response;

import com.rudy.auth.client.dto.ClientDto;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class ClientSearchResponse {
    private final String clientId;
    private final String clientSecret;

    protected ClientSearchResponse(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public static ClientSearchResponse of(ClientDto clientDto) {
        String clientId = clientDto.getClientId();
        String clientSecret = clientDto.getClientSecret();

        Assert.hasText(clientId, "client id must not be empty");
        Assert.hasText(clientSecret, "client secret must not be empty");
        return new ClientSearchResponse(clientId, clientSecret);
    }
}
