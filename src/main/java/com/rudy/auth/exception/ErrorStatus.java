package com.rudy.auth.exception;

import lombok.Getter;

@Getter
public enum ErrorStatus {
    ;

    private final int status;
    private final String message;

    ErrorStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
