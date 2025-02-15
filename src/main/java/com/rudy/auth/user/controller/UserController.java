package com.rudy.auth.user.controller;

import com.rudy.auth.user.request.UserRegisterRequest;
import com.rudy.auth.user.response.UserRegisterResponse;
import com.rudy.auth.user.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRegisterService userRegisterService;

    @PostMapping
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        UserRegisterResponse register = userRegisterService.register(userRegisterRequest);
        return ResponseEntity.ok(register);
    }

}
