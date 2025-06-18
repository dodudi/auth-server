package com.rudy.auth.global.response;

import lombok.Getter;

@Getter
public class ApiResponse {
    private final int status;
    private final String message;
    private final Object data;

    protected ApiResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse ok(String message, Object data) {
        return new ApiResponse(200, message, data);
    }
}
