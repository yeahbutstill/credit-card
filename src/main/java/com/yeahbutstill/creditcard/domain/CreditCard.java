package com.yeahbutstill.creditcard.domain;

import com.yeahbutstill.creditcard.interceptor.EncryptedString;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    @EncryptedString
    private String creditCardNumber;

    @NotNull
    @Size(max = 4)
    private String cvv;

    @NotNull
    @Size(max = 7)
    private String expirationDate;

}
