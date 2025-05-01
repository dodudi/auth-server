package com.rudy.auth.exception;

import lombok.Getter;

@Getter
public enum ErrorStatus {
    DUPLICATE_USERNAME(404, "already exists username"),
    BAD_CREDENTIAL(401, "username or password incorrect");

    private final int status;
    private final String message;

    ErrorStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
