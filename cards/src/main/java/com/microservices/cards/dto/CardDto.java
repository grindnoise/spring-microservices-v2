package com.microservices.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(name = "Card", description = "Schema to hold Card information")
public record CardDto(
        @NotEmpty(message = "Mobile number cannot be empty")
        @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
        @Schema(description = "Mobile number of the card owner")
        String mobileNumber,
        @NotEmpty(message = "Card number cannot be empty")
        @Size(max = 100, message = "Card number cannot be greater than 100")
        @Schema(description = "Card number")
        String cardNumber,
        @NotEmpty(message = "Card type cannot be empty")
        @Size(max = 100, message = "Card type cannot be greater than 100")
        @Schema(description = "Card type")
        String cardType,
        @Positive(message = "Total card limit should be greater than zero")
        @Schema(description = "Total limit")
        Integer totalLimit,
        @PositiveOrZero(message = "Available amount should be greater than or equal to zero")
        @Schema(description = "Amount used")
        Integer availableAmount,
        @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
        @Schema(description = "Total amount used by a Customer", example = "1000")
        Integer amountUsed
) {
}
