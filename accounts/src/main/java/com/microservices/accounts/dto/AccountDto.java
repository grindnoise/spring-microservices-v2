package com.microservices.accounts.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {

    @Min(value = 1000000000L, message = "Account number must be 10 digits")
    @Max(value = 9999999999L, message = "Account number must be 10 digits")
    private Long accountNumber;

    @NotEmpty
    @Size(max = 100, message = "Account type cannot be longer than 100 characters")
    private String accountType;

    @NotNull
    @Size(max = 200, message = "Branch address cannot be longer than 200 characters")
    private String branchAddress;

}
