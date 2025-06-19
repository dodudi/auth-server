package com.rudy.auth.test.init;

import com.rudy.auth.client.request.ClientRegisterRequest;
import com.rudy.auth.client.service.ClientInfoService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 테스트 환경에서 사용하기 위한 초기화 클래스 <br>
 * MSA 서비스에서 사용하는 서비스 클라이언트 등록을 여기에서 추가 <br>
 * 테스트 진행 전 clientId, clientSecret 에 맞는 서비스에 테스트용 설정파일에 추가
 */
@Slf4j
@Profile("test")
@Component
@RequiredArgsConstructor
public class InitClientRegister {

    private final ClientInfoService clientInfoService;

    private static final Map<String, String> mapper = Map.of(
            "blog-server", "srqKbAmAhJrfO-Dve9tl39mX1izGcBF4dsC9DK30Ofg_Nt5NyDsusoWlgYEgEIsl"
    );

    @PostConstruct
    public void init() {
        mapper.forEach((clientId, clientSecret) -> {
            ClientRegisterRequest clientRegisterRequest = new ClientRegisterRequest("blog-server", "srqKbAmAhJrfO-Dve9tl39mX1izGcBF4dsC9DK30Ofg_Nt5NyDsusoWlgYEgEIsl");
            clientInfoService.register(clientRegisterRequest);
            log.info("REGISTER - clientId: {} | clientSecret: {}", clientId, clientSecret);
        });
    }
}
