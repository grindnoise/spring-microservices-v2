package com.microservices.Cards.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String string, Object... args) {
        super(String.format(string, args));
    }
}
