package com.driveassist.common.exception;

import org.springframework.http.HttpStatus;

public class ServiceUnavailableException extends BaseException {

    public ServiceUnavailableException(String serviceName) {
        super(serviceName + " is currently unavailable", HttpStatus.SERVICE_UNAVAILABLE, "SERVICE_UNAVAILABLE");
    }
}
