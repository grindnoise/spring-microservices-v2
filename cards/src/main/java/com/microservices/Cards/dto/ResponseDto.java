package com.microservices.Cards.dto;

import org.springframework.http.HttpStatus;

public record ResponseDto(
        String message,
        HttpStatus statusCode
) {
}
