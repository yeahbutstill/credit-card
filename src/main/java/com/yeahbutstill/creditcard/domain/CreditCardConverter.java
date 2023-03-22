package com.yeahbutstill.creditcard.domain;

import com.yeahbutstill.creditcard.config.SpringContextHelper;
import com.yeahbutstill.creditcard.service.EncryptionService;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CreditCardConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return getEncryptionService().encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return getEncryptionService().decrypt(dbData);
    }

    private EncryptionService getEncryptionService() {
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }

}
