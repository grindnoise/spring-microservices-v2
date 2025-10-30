package com.microservices.Cards.dto;

public record CardDto(
        String mobileNumber,
        String cardNumber,
        String cardType,
        Integer totalLimit,
        Integer amountUsed,
        Integer availableAmount
) {
}
