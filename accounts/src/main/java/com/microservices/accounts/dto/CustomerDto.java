package com.microservices.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(name = "Customer", description = "Customer details")
public class CustomerDto {

    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Schema(description = "Name of the customer", example = "John Doe")
    private String name;

    @Email
    @Schema(description = "Email of the customer", example = "john.doe@example.com")
    private String email;

    @Size(min = 10, max = 10, message = "Mobile number must be 10 digits")
    @Schema(name = "Mobile number of the customer", example = "1234567890")
    private String mobileNumber;

    @JsonProperty("accountsDto")
    @JsonDeserialize(using = AccountListDeserializer.class)
    @Builder.Default
    private List<AccountDto> accounts = List.of();

}
