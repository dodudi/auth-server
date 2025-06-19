package com.rudy.auth.client.controller;

import com.rudy.auth.client.dto.ClientDto;
import com.rudy.auth.client.request.ClientRegisterRequest;
import com.rudy.auth.client.request.ClientSearchRequest;
import com.rudy.auth.client.response.ClientSearchResponse;
import com.rudy.auth.client.service.ClientInfoService;
import com.rudy.auth.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientInfoController {

    private final ClientInfoService clientInfoService;

    @PostMapping
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody ClientRegisterRequest clientRegisterRequest) {
        String clientId = clientInfoService.register(clientRegisterRequest);
        return ResponseEntity.ok(ApiResponse.ok("register client success", clientId));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> search(@Valid @RequestBody ClientSearchRequest clientSearchRequest) {
        ClientDto search = clientInfoService.search(clientSearchRequest);
        return ResponseEntity.ok(ApiResponse.ok("search client success", ClientSearchResponse.of(search)));
    }
}
