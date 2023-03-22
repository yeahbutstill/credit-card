package com.yeahbutstill.creditcard.service;

public interface EncryptionService {

    String encrypt(String freeText);

    String decrypt(String encryptedText);
}
