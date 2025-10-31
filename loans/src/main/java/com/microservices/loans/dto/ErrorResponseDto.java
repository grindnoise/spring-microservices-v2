package com.microservices.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public record ErrorResponseDto(
        @Schema(description = "HTTP status code")
        HttpStatus statusCode,
        @Schema(description = "Error message")
        String message,
        @Schema(description = "Request path")
        String path,
        @Schema(description = "Timestamp of the error")
        Instant timestamp
) {

    public static ErrorResponseDto of(HttpStatus statusCode, String message, String path) {
        return new ErrorResponseDto(statusCode, message, path, Instant.now());
    }
}
