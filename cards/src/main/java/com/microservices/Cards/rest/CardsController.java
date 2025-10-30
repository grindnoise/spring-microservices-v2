package com.microservices.Cards.rest;

import com.microservices.Cards.dto.CardDto;
import com.microservices.Cards.dto.ResponseDto;
import com.microservices.Cards.service.CardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CardsController {

    private final CardService cardService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam @Pattern(regexp = "^\\d{10}$") String mobileNumber) {
        cardService.createCard(mobileNumber);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto("Card created successfully", HttpStatus.CREATED));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardDto> fetchCard(@Valid @RequestParam @Pattern(regexp = "^\\d{10}$") String mobileNumber) {
        return ResponseEntity.ok(cardService.fetchCard(mobileNumber));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(@Valid @RequestParam @Pattern(regexp = "^\\d{10}$") String mobileNumber) {
        cardService.deleteCard(mobileNumber);
        return ResponseEntity.ok(new ResponseDto("Card deleted successfully", HttpStatus.OK));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@RequestBody @Valid CardDto cardDto) {
        cardService.updateCard(cardDto);
        return ResponseEntity.ok(new ResponseDto("Card updated successfully", HttpStatus.OK));
    }

}
