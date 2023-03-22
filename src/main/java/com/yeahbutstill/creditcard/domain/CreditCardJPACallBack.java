package com.yeahbutstill.creditcard.domain;

import com.yeahbutstill.creditcard.config.SpringContextHelper;
import com.yeahbutstill.creditcard.service.EncryptionService;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreditCardJPACallBack {

    @PrePersist
    @PreUpdate
    public void beforeInsertOrUpdateCallBack(CreditCard creditCard) {
        log.info("before update was called...");
        creditCard.setCreditCardNumber(getEncryptionService().encrypt(creditCard.getCreditCardNumber()));
    }

    @PostPersist
    @PostLoad
    @PostUpdate
    public void postLoad(CreditCard creditCard) {
        log.info("postLoad was called...");
        creditCard.setCreditCardNumber(getEncryptionService().decrypt(creditCard.getCreditCardNumber()));
    }

    private EncryptionService getEncryptionService() {
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }

}
