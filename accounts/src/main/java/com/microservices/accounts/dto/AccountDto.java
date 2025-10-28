package com.microservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "Account", description = "Account details")
public class AccountDto {

    @Min(value = 1000000000L, message = "Account number must be 10 digits")
    @Max(value = 9999999999L, message = "Account number must be 10 digits")
    @Schema(description = "Account number", example = "1234567890")
    private Long accountNumber;

    @NotEmpty
    @Size(max = 100, message = "Account type cannot be longer than 100 characters")
    @Schema(description = "Saving or Current", example = "Saving")
    private String accountType;

    @NotNull
    @Size(max = 200, message = "Branch address cannot be longer than 200 characters")
    @Schema(description = "Branch address", example = "123 Main St, Anytown, USA")
    private String branchAddress;

}
