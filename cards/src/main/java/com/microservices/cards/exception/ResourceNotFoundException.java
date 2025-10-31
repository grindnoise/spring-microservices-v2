package com.microservices.cards.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String string, Object... args) {
        super(String.format(string, args));
    }
}
