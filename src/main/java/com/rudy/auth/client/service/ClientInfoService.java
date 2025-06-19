package com.rudy.auth.client.service;

import com.rudy.auth.client.domain.ClientInfo;
import com.rudy.auth.client.repository.ClientInfoRepository;
import com.rudy.auth.client.request.ClientRegistRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class ClientInfoService {

    private final ClientInfoRepository clientInfoRepository;

    @Transactional
    public String register(ClientRegistRequest clientRegistRequest) {
        final String clientId = clientRegistRequest.getClientId();
        final String clientSecret = clientRegistRequest.getClientSecret();
        Assert.hasText(clientId, "client id must not be null");
        Assert.hasText(clientSecret, "client secret must not be null");

        //시크릿 아이디가 존재하면 기존 객체 조회하고, 없으면 새로운 객체 생성
        ClientInfo clientInfo = clientInfoRepository.findByClientId(clientId).orElseGet(() -> new ClientInfo(clientId, clientSecret));
        clientInfoRepository.save(clientInfo);
        return clientInfo.getClientId();
    }
}
