package com.rudy.auth.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final int status;

    public CustomException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.status = errorStatus.getStatus();
    }

    public CustomException(ErrorStatus errorStatus, Throwable cause) {
        super(errorStatus.getMessage(), cause);
        this.status = errorStatus.getStatus();
    }
}
