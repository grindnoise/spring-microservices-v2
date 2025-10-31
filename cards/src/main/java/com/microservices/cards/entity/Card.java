package com.microservices.cards.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cards")
public class Card extends BaseEntity {

    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Mobile number cannot be empty")
    @Column(name = "mobile_number", nullable = false, length = 15)
    @Size(max = 15, message = "Mobile number cannot be greater than 15")
    private String mobileNumber;

    @NotEmpty(message = "Card number cannot be empty")
    @Column(name = "card_number", nullable = false, length = 100)
    @Size(max = 100, message = "Card number cannot be greater than 100")
    private String cardNumber;

    @NotEmpty(message = "Card type cannot be empty")
    @Column(name = "card_type", nullable = false, length = 100)
    @Size(max = 100, message = "Card type cannot be greater than 100")
    private String cardType;

    @NotNull
    @Column(name = "total_limit", nullable = false)
    private Integer totalLimit;

    @NotNull
    @Column(name = "amount_used", nullable = false)
    private Integer amountUsed;

    @NotNull
    @Column(name = "available_amount", nullable = false)
    private Integer availableAmount;


}
