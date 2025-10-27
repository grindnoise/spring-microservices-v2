package com.microservices.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ErrorDto {

    private HttpStatus errorCode;

    private String apiPath;

    private String errorMessage;

    private LocalDateTime timestamp;
}
