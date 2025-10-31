package com.microservices.cards.service;

import com.microservices.cards.constants.CardsConstants;
import com.microservices.cards.dto.CardDto;
import com.microservices.cards.entity.Card;
import com.microservices.cards.exception.CardAlreadyExistsException;
import com.microservices.cards.exception.ResourceNotFoundException;
import com.microservices.cards.mapper.CardMapper;
import com.microservices.cards.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private static final CardMapper cardMapper = CardMapper.INSTANCE;

    @Override
    public void createCard(String mobileNumber) {
        final Optional<Card> cardOptional = cardRepository.findByMobileNumber(mobileNumber);
        if (cardOptional.isPresent()) {
            throw new CardAlreadyExistsException("Card already exists for mobile number: " + mobileNumber);
        }

        cardRepository.save(Card.builder()
                .mobileNumber(mobileNumber)
                .cardNumber(String.valueOf(ThreadLocalRandom.current().nextLong(100000000000L, 999999999999L)))
                .cardType(CardsConstants.CREDIT_CARD)
                .totalLimit(CardsConstants.NEW_CARD_LIMIT)
                .availableAmount(CardsConstants.NEW_CARD_LIMIT)
                .amountUsed(0)
                .build());
    }

    @Override
    public CardDto fetchCard(String mobileNumber) {
        return cardMapper.toDto(cardRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
                new ResourceNotFoundException("Card not found for mobile number: " + mobileNumber)));
    }

    @Override
    public boolean updateCard(CardDto cardDto) {
        final var card = cardRepository.findByMobileNumber(cardDto.mobileNumber()).orElseThrow(() ->
                new ResourceNotFoundException("Card not found for mobile number: " + cardDto.mobileNumber()));

        var hasChanged = false;

        if (!Objects.equals(card.getCardNumber(), cardDto.cardNumber())) {
            card.setCardNumber(cardDto.cardNumber());
            hasChanged = true;
        }

        if (!Objects.equals(card.getCardType(), cardDto.cardType())) {
            card.setCardType(cardDto.cardType());
            hasChanged = true;
        }

        if (!Objects.equals(card.getTotalLimit(), cardDto.totalLimit())) {
            card.setTotalLimit(cardDto.totalLimit());
            hasChanged = true;
        }

        if (!Objects.equals(card.getAvailableAmount(), cardDto.availableAmount())) {
            card.setAvailableAmount(cardDto.availableAmount());
            hasChanged = true;
        }

        if (!Objects.equals(card.getAmountUsed(), cardDto.amountUsed())) {
            card.setAmountUsed(cardDto.amountUsed());
            hasChanged = true;
        }

        if (hasChanged) {
            cardRepository.save(card);
        }

        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        final var card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
                new ResourceNotFoundException("Card not found for mobile number: " + mobileNumber));
        cardRepository.delete(card);
        return true;
    }
}
