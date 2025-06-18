package com.rudy.auth.client.domain;

import com.rudy.auth.global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ClientInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String clientId;
    private String clientSecret;
    private String scope;
    private String accessToken;
    private String refreshToken;

    public ClientInfo(String clientId, String clientSecret, String scope, String accessToken, String refreshToken) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
