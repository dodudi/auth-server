package com.rudy.auth.client.service;

import com.rudy.auth.client.domain.ClientInfo;
import com.rudy.auth.client.dto.ClientDto;
import com.rudy.auth.client.repository.ClientInfoRepository;
import com.rudy.auth.client.request.ClientRegisterRequest;
import com.rudy.auth.client.request.ClientSearchRequest;
import com.rudy.auth.exception.CustomException;
import com.rudy.auth.exception.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class ClientInfoService {

    private final ClientInfoRepository clientInfoRepository;

    /**
     * clientId, clientSecret 을 등록하기 위한 메소드
     *
     * @param clientRegisterRequest {@link ClientRegisterRequest}
     */
    @Transactional
    public String register(ClientRegisterRequest clientRegisterRequest) {
        final String clientId = clientRegisterRequest.getClientId();
        final String clientSecret = clientRegisterRequest.getClientSecret();
        Assert.hasText(clientId, "client id must not be null");
        Assert.hasText(clientSecret, "client secret must not be null");

        //시크릿 아이디가 존재하면 기존 객체 조회하고, 없으면 새로운 객체 생성
        ClientInfo clientInfo = clientInfoRepository.findByClientId(clientId).orElseGet(() -> new ClientInfo(clientId, clientSecret));
        clientInfoRepository.save(clientInfo);
        return clientInfo.getClientId();
    }

    /**
     * clientId, clientSecret 에 해당하는 클라이언트가 있는지 확인하고 반환하는 메소드
     *
     * @param clientSearchRequest {@link ClientSearchRequest}}
     */
    public ClientDto search(ClientSearchRequest clientSearchRequest) {
        final String clientId = clientSearchRequest.getClientId();
        final String clientSecret = clientSearchRequest.getClientSecret();
        Assert.hasText(clientId, "client id must not be null");
        Assert.hasText(clientSecret, "client secret must not be null");

        ClientInfo clientInfo = clientInfoRepository.findByClientIdAndClientSecret(clientId, clientSecret)
                .orElseThrow(() -> new CustomException(ErrorStatus.BAD_CREDENTIAL_CLIENT));

        return new ClientDto(clientInfo.getClientId(), clientInfo.getClientSecret());
    }
}
