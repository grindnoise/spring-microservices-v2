package com.microservices.Cards.exception;

public class CardAlreadyExistsException extends  RuntimeException {
    public CardAlreadyExistsException(String message) {
        super(message);
    }
}
