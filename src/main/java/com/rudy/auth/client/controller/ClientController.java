package com.rudy.auth.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {

    @PostMapping
    public ResponseEntity<Void> register() {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Void> get() {
        return ResponseEntity.ok().build();
    }
}
