package com.driveassist.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Abstract base exception — all custom exceptions inherit from this.
 * Demonstrates OOP Inheritance in exception hierarchy.
 */
public abstract class BaseException extends RuntimeException {

    private final HttpStatus status;
    private final String errorCode;

    protected BaseException(String message, HttpStatus status, String errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() { return status; }

    public String getErrorCode() { return errorCode; }
}
