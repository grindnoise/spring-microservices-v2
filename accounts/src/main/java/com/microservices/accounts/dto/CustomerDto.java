package com.microservices.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerDto {

    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    private String email;

    private String mobileNumber;

    @JsonProperty("accountsDto")
    @JsonDeserialize(using = AccountListDeserializer.class)
    @Builder.Default
    private List<AccountDto> accounts = List.of();

}
