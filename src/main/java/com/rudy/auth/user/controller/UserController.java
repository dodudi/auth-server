package com.rudy.auth.user.controller;

import com.rudy.auth.user.request.UserRegisterRequest;
import com.rudy.auth.user.request.UserRoleUpdateRequest;
import com.rudy.auth.user.response.UserInfoResponse;
import com.rudy.auth.user.service.UserRegisterService;
import com.rudy.auth.user.service.UserRoleUpdateService;
import com.rudy.auth.user.service.UserSearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRoleUpdateService userRoleUpdateService;
    private final UserRegisterService userRegisterService;
    private final UserSearchService userSearchService;

    @GetMapping
    public ResponseEntity<List<UserInfoResponse>> getUsers(Pageable pageable) {
        List<UserInfoResponse> response = userSearchService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserInfoResponse> register(@RequestBody @Valid UserRegisterRequest request) {
        UserInfoResponse register = userRegisterService.register(request);
        return ResponseEntity.ok(register);
    }

    @PutMapping("/{userId}/roles")
    public ResponseEntity<UserInfoResponse> updateRoles(@PathVariable("userId") Integer userId, @RequestBody UserRoleUpdateRequest request) {
        UserInfoResponse updateRole = userRoleUpdateService.updateRoles(userId, request);
        return ResponseEntity.ok(updateRole);
    }
}
