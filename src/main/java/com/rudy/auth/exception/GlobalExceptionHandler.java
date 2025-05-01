package com.rudy.auth.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customExceptionHandler(CustomException e) {
        log.error("Exception Error: {}", e.getMessage(), e);
        return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getMessage(), e.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {
        log.error("Exception Error: {}", e.getMessage(), e);
        return ResponseEntity.status(500).body(new ErrorResponse(e.getMessage(), 500));
    }
}
