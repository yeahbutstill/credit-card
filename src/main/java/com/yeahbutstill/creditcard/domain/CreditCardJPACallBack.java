package com.yeahbutstill.creditcard.domain;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreditCardJPACallBack {

    @PrePersist
    @PreUpdate
    public void beforeInsertOrUpdateCallBack(CreditCard creditCard) {
        log.info("before update was called...");
    }


    @PostPersist
    @PostLoad
    @PostUpdate
    public void postLoad(CreditCard creditCard) {
        log.info("postLoad was called...");
    }

}
