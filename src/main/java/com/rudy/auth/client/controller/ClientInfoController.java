package com.rudy.auth.client.controller;

import com.rudy.auth.client.request.ClientRegistRequest;
import com.rudy.auth.client.service.ClientInfoService;
import com.rudy.auth.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientInfoController {

    private final ClientInfoService clientInfoService;

    @PostMapping
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody ClientRegistRequest clientRegistRequest) {
        String clientId = clientInfoService.register(clientRegistRequest);
        return ResponseEntity.ok(ApiResponse.ok("regster client success", clientId));
    }
}
