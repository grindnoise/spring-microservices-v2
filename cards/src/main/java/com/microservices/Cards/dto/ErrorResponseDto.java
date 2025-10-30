package com.microservices.Cards.dto;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ErrorResponseDto(
        HttpStatus statusCode,
        String message,
        String path,
        Instant timestamp
) {

    public static ErrorResponseDto of(HttpStatus statusCode, String message, String path) {
        return new ErrorResponseDto(statusCode, message, path, Instant.now());
    }
}
