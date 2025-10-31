package com.microservices.loans.rest;

import com.microservices.loans.dto.LoanDto;
import com.microservices.loans.dto.ResponseDto;
import com.microservices.loans.service.LoanService;
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
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam @Valid @Pattern(regexp = "^\\d{10}$") String mobileNumber) {
        loanService.createLoan(mobileNumber);
        return ResponseEntity.ok().body(new ResponseDto("Loan created successfully", HttpStatus.OK));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody @Valid LoanDto loanDto) {
        loanService.updateLoan(loanDto);
        return ResponseEntity.ok().body(new ResponseDto("Loan updated successfully", HttpStatus.OK));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoan(@RequestParam @Valid @Pattern(regexp = "^\\d{10}$") String mobileNumber) {
        return ResponseEntity.ok().body(loanService.fetchLoan(HtmlUtils.htmlEscape(mobileNumber)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam @Valid @Pattern(regexp = "^\\d{10}$") String mobileNumber) {
        loanService.deleteLoan(mobileNumber);
        return ResponseEntity.ok().body(new ResponseDto("Loan deleted successfully", HttpStatus.OK));
    }
}
