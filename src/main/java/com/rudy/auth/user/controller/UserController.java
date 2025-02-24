package com.rudy.auth.user.controller;

import com.rudy.auth.user.request.UserRegisterRequest;
import com.rudy.auth.user.response.UserRegisterResponse;
import com.rudy.auth.user.response.UserSearchResponse;
import com.rudy.auth.user.service.UserRegisterService;
import com.rudy.auth.user.service.UserSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRegisterService userRegisterService;
    private final UserSearchService userSearchService;

    @GetMapping
    public ResponseEntity<List<UserSearchResponse>> getUserRegister(Pageable pageable) {
        List<UserSearchResponse> response = userSearchService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        UserRegisterResponse register = userRegisterService.register(userRegisterRequest);
        return ResponseEntity.ok(register);
    }

}
