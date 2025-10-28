package com.microservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Schema(name = "Error", description = "Error object")
public class ErrorDto {

    @Schema(description = "Error code", example = "404")
    private HttpStatus errorCode;

    @Schema(description = "Api invoked path")
    private String apiPath;

    @Schema(description = "Error message")
    private String errorMessage;

    @Schema(description = "Timestamp")
    private LocalDateTime timestamp;
}
