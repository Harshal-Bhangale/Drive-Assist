package com.driveassist.common.dto;

import java.time.Instant;

/**
 * Generic API response wrapper — uses Builder pattern.
 * Provides a consistent response structure across all microservices.
 */
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private Instant timestamp;

    private ApiResponse() {
        this.timestamp = Instant.now();
    }

    // --- Static factory methods (Factory Pattern) ---

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.message = "Operation successful";
        response.data = data;
        return response;
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.message = message;
        response.data = data;
        return response;
    }

    public static <T> ApiResponse<T> error(String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = false;
        response.message = message;
        response.data = null;
        return response;
    }

    public static <T> ApiResponse<T> error(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = false;
        response.message = message;
        response.data = data;
        return response;
    }

    // --- Getters ---

    public boolean isSuccess() { return success; }

    public String getMessage() { return message; }

    public T getData() { return data; }

    public Instant getTimestamp() { return timestamp; }
}
