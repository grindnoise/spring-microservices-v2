package com.microservices.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(name = "loan", description = "Loan details")
public record LoanDto(
        @NotEmpty(message = "Mobile number is required")
        @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
        @Schema(description = "Mobile number of the customer", example = "1234567890")
        String mobileNumber,
        @NotEmpty(message = "Loan number is required")
        @Schema(description = "Loan number of the customer", example = "1234567890")
        String loanNumber,
        @NotEmpty(message = "Loan type is required")
        @Schema(description = "Loan type of the customer", example = "Home Loan")
        String loanType,
        @Schema(description = "Total loan amount", example = "100000")
        @PositiveOrZero(message = "Total loan amount must be positive or zero")
        Integer totalLoan,
        @Schema(description = "Amount paid", example = "50000")
        @PositiveOrZero(message = "Amount paid must be positive or zero")
        Integer amountPaid,
        @Schema(description = "Outstanding amount", example = "50000")
        @PositiveOrZero(message = "Outstanding amount must be positive or zero")
        Integer outstandingAmount
) {
}
