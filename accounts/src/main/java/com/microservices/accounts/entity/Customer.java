package com.microservices.accounts.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @NotNull
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @Email
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NotNull
    @Size(max = 20, message = "Mobile number cannot be longer than 20 characters")
    @Column(name = "mobile_number", nullable = false, length = 20)
    private String mobileNumber;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;
}
