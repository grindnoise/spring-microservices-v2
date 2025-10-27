package com.microservices.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException(String s) {
        super(s);
    }

    public CustomerAlreadyExistsException(String s, Object... objects) {
        super(String.format(s, objects));
    }

}
