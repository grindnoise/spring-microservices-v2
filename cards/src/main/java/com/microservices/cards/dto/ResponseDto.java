package com.microservices.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

@Schema(name = "Response", description = "Response information")
public record ResponseDto(
        @Schema(description = "Response message")
        String message,
        @Schema(description = "Response status code")
        HttpStatus statusCode
) {
}
