package com.microservices.accounts.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

    @Id
    @Min(value = 1000000000L, message = "Account number must be 10 digits")
    @Max(value = 9999999999L, message = "Account number must be 10 digits")
    @Column(name = "account_number", nullable = false, unique = true)
    private Long accountNumber;

    @NotEmpty
    @Size(max = 100, message = "Account type cannot be longer than 100 characters")
    @Column(name = "account_type", nullable = false, length = 100)
    private String accountType;

    @NotNull
    @Size(max = 200, message = "Branch address cannot be longer than 200 characters")
    @Column(name = "branch_address", nullable = false, length = 200)
    private String branchAddress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
