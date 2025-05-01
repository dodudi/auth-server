package com.rudy.auth.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final int status;
    private final String message;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public ErrorResponse(ErrorStatus errorStatus) {
        this(errorStatus.getMessage(), errorStatus.getStatus());
    }
}
