package com.microservices.accounts.rest;

import com.microservices.accounts.constants.AccountsConstants;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.dto.ResponseDto;
import com.microservices.accounts.service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Accounts", description = "Accounts API")
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountsService accountsService;

    @Operation(summary = "Create account", description = "Create a new account")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody @Valid CustomerDto customerDto) {

        accountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(summary = "Update account", description = "Update an existing account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody @Valid CustomerDto customerDto) {

        accountsService.updateAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }

    @Operation(summary = "Fetch account", description = "Fetch an existing account")
    @ApiResponse(responseCode = "200", description = "Account fetched successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccount(@RequestParam
                                                    @Pattern(regexp = "^[0-9]{10}$", message = "Account number must be 10 digits")
                                                    String mobileNumber) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsService.fetchAccount(mobileNumber));
    }

    @Operation(summary = "Delete account", description = "Delete an existing account")
    @ApiResponse(responseCode = "200", description = "Account deleted successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                     @Pattern(regexp = "^[0-9]{10}$", message = "Account number must be 10 digits")
                                                     String mobileNumber) {
        accountsService.deleteAccount(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }
}
