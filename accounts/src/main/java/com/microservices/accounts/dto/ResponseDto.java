package com.microservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Response object")
public class ResponseDto {

    @Schema(description = "Status code", example = "200")
    private String statusCode;

    @Schema(description = "Message", example = "Account created successfully")
    private String message;
}
