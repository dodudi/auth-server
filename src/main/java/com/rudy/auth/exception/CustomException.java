package com.rudy.auth.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorStatus errorStatus;

    public CustomException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }

    public CustomException(ErrorStatus errorStatus, Throwable cause) {
        super(errorStatus.getMessage(), cause);
        this.errorStatus = errorStatus;
    }
}
