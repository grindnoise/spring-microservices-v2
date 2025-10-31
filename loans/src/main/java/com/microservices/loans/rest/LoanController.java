package com.microservices.loans.rest;

import com.microservices.loans.dto.LoanDto;
import com.microservices.loans.dto.ResponseDto;
import com.microservices.loans.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@Validated
@RestController
@RequestMapping("/api")
@Tag(name = "Loans", description = "Loans API")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @Operation(summary = "Create a new loan", description = "Create a new loan for a customer")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam @Valid @Pattern(regexp = "^\\d{10}$") String mobileNumber) {
        loanService.createLoan(mobileNumber);
        return ResponseEntity.ok().body(new ResponseDto("Loan created successfully", HttpStatus.OK));
    }

    @Operation(summary = "Update an existing loan", description = "Update an existing loan for a customer")
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody @Valid LoanDto loanDto) {
        loanService.updateLoan(loanDto);
        return ResponseEntity.ok().body(new ResponseDto("Loan updated successfully", HttpStatus.OK));
    }

    @Operation(summary = "Fetch a loan by mobile number", description = "Fetch a loan by mobile number")
    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoan(@RequestParam @Valid @Pattern(regexp = "^\\d{10}$") String mobileNumber) {
        return ResponseEntity.ok().body(loanService.fetchLoan(HtmlUtils.htmlEscape(mobileNumber)));
    }

    @Operation(summary = "Delete a loan by mobile number", description = "Delete a loan by mobile number")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam @Valid @Pattern(regexp = "^\\d{10}$") String mobileNumber) {
        loanService.deleteLoan(mobileNumber);
        return ResponseEntity.ok().body(new ResponseDto("Loan deleted successfully", HttpStatus.OK));
    }
}
