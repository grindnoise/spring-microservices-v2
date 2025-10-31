package com.microservices.cards.rest;

import com.microservices.cards.dto.CardDto;
import com.microservices.cards.dto.ErrorResponseDto;
import com.microservices.cards.dto.ResponseDto;
import com.microservices.cards.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Cards", description = "Cards API")
public class CardsController {

    private final CardService cardService;

    @Operation(summary = "Create a new card", description = "Create a new card for a customer")
    @ApiResponse(responseCode = "201", description = "Card created successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam @Pattern(regexp = "^\\d{10}$") String mobileNumber) {
        cardService.createCard(mobileNumber);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto("Card created successfully", HttpStatus.CREATED));
    }

    @Operation(summary = "Fetch a card", description = "Fetch a card for a customer")
    @ApiResponse(responseCode = "200", description = "Card fetched successfully")
    @ApiResponse(responseCode = "400", description = "Invalid mobile number", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    @GetMapping("/fetch")
    public ResponseEntity<CardDto> fetchCard(@Valid @RequestParam @Pattern(regexp = "^\\d{10}$") String mobileNumber) {
        return ResponseEntity.ok(cardService.fetchCard(mobileNumber));
    }

    @Operation(summary = "Delete a card", description = "Delete a card for a customer")
    @ApiResponse(responseCode = "200", description = "Card deleted successfully")
    @ApiResponse(responseCode = "400", description = "Invalid mobile number", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(@Valid @RequestParam @Pattern(regexp = "^\\d{10}$") String mobileNumber) {
        cardService.deleteCard(mobileNumber);
        return ResponseEntity.ok(new ResponseDto("Card deleted successfully", HttpStatus.OK));
    }

    @Operation(summary = "Update a card", description = "Update a card for a customer")
    @ApiResponse(responseCode = "200", description = "Card updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@RequestBody @Valid CardDto cardDto) {
        cardService.updateCard(cardDto);
        return ResponseEntity.ok(new ResponseDto("Card updated successfully", HttpStatus.OK));
    }

}
