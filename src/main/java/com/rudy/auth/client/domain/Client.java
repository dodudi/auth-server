package com.rudy.auth.client.domain;

import com.rudy.auth.global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Client extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String clientId;
    private String clientSecret;
    private String grantType;
    private String scope;
    private String redirectUri;
    private String accessTokenValidity;
    private String refreshTokenValidity;
    private String resourceIds;
    private String autoApprove;
    private String authorities;
}
