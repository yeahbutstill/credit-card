package com.yeahbutstill.creditcard.domain;

import com.yeahbutstill.creditcard.interceptor.EncryptedString;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(CreditCardJPACallBack.class)
@Slf4j
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CreditCard that = (CreditCard) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @PrePersist
    public void prePersistCallBack() {
        log.info("JPA PrePersist Callback Was Called");
    }
}
