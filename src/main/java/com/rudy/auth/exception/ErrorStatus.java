package com.rudy.auth.exception;

import lombok.Getter;

@Getter
public enum ErrorStatus {
    DUPLICATE_USER_NAME(404, "already exists username");

    private final int status;
    private final String message;

    ErrorStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
