package com.unite.challenge.validator;

import com.unite.challenge.entity.Item;
import com.unite.challenge.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemValidatorTest {

    private Properties messages;

    @BeforeAll
    void initialize() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        messages = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(Constants.PROPERTIES_FILE_NAME)) {
            messages.load(resourceStream);
        } catch (IOException e) {
            log.error("Error reading properties file");
        }
    }

    @Test
    @DisplayName("Should validate if item is valid")
    void shouldValidateIfValidItem() {
        ItemValidator validator = new ItemValidator(messages);
        assertTrue(validator.isValid(new Item(1, 12.44, 32.45)));
        assertTrue(validator.getErrors().isEmpty());
    }

    @Test
    @DisplayName("Should not validate if invalid weight")
    void shouldNotValidateIfInvalidWeight() {
        ItemValidator validator = new ItemValidator(messages);
        assertFalse(validator.isValid(new Item(1, 112.44, 32.45)));
        assertTrue(validator.getErrors().contains("1: Item weight should be between 0 and 100"));
    }

    @Test
    @DisplayName("Should not validate if invalid cost")
    void shouldNotValidateIfInvalidCost() {
        ItemValidator validator = new ItemValidator(messages);
        assertFalse(validator.isValid(new Item(1, 99.44, 120.00)));
        assertTrue(validator.getErrors().contains("1: Item cost should be less than 100"));
    }
}