package com.yeahbutstill.creditcard.repository;

import com.yeahbutstill.creditcard.domain.CreditCard;
import com.yeahbutstill.creditcard.service.EncryptionService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;


@ComponentScan(basePackageClasses = {EncryptionService.class})
@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@Slf4j
class CreditCardRepositoryTest {

    @Container
    static PostgreSQLContainer<?> psql = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15"));
    final String CREDIT_CARD = "12345678900000";
    @Autowired
    EncryptionService encryptionService;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @DynamicPropertySource
    static void configureTestContainersProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", psql::getJdbcUrl);
        registry.add("spring.datasource.username", psql::getUsername);
        registry.add("spring.datasource.password", psql::getPassword);
    }

    @Test
    void testSaveAndStoreCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(CREDIT_CARD);
        creditCard.setCvv("123");
        creditCard.setExpirationDate("12/2028");

        CreditCard savedCC = creditCardRepository.saveAndFlush(creditCard);

        log.info("Getting CC from database: {}", savedCC.getCreditCardNumber());

        log.info("CC At Rest");
        log.info("CC Encrypted: {}", encryptionService.encrypt(CREDIT_CARD));

        Map<String, Object> dbRow = jdbcTemplate.queryForMap("SELECT * FROM credit_cards " +
                "WHERE id = " + savedCC.getId());

        String dbCardValue = dbRow.get("credit_card_number").toString();

        Assertions.assertThat(savedCC.getCreditCardNumber()).isEqualTo(dbCardValue);
        Assertions.assertThat(dbCardValue).isNotEqualTo(encryptionService.encrypt(CREDIT_CARD));

        CreditCard fetchedCC = creditCardRepository.findById(savedCC.getId()).orElseGet(CreditCard::new);
        Assertions.assertThat(savedCC.getCreditCardNumber()).isEqualTo(fetchedCC.getCreditCardNumber());

    }

}