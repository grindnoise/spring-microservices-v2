package com.microservices.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s.%s not found for value %s", resourceName, fieldName, fieldValue));
    }

    public ResourceNotFoundException(String s, Object... objects) {
        super(String.format(s, objects));
    }

}
