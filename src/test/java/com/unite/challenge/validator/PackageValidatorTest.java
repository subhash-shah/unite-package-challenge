package com.unite.challenge.validator;

import com.unite.challenge.entity.Item;
import com.unite.challenge.entity.Package;
import com.unite.challenge.factory.PackageFactory;
import com.unite.challenge.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PackageValidatorTest {

    private Properties messages;
    private List<Item> items = new ArrayList<>();

    @BeforeEach
    void initialize() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        messages = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(Constants.PROPERTIES_FILE_NAME)) {
            messages.load(resourceStream);
        } catch (IOException e) {
            log.error("Error reading properties file");
        }

        Item firstItem = new Item(1, 52.98, 22.98);
        Item secondItem = new Item(2, 22.45, 45.23);
        Item thirdItem = new Item(3, 49.44, 87.43);
        items.add(firstItem);
        items.add(secondItem);
        items.add(thirdItem);
    }

    @Test
    @DisplayName("Should validate if package is valid")
    void shouldCreatePackage() {
        final Package aPackage = PackageFactory.createPackage(88.34, items);
        final PackageValidator packageValidator = new PackageValidator(messages);
        assertTrue(packageValidator.isValid(aPackage));
        assertFalse(packageValidator.getPackageError().hasErrors());
    }

    @Test
    @DisplayName("Should have error if invalid weight")
    void shouldHaveErrorIfInvalidWeight() {
        final Package aPackage = PackageFactory.createPackage(100.92, items);
        final PackageValidator packageValidator = new PackageValidator(messages);
        assertFalse(packageValidator.isValid(aPackage));
        assertTrue(packageValidator.getPackageError().getErrors().contains("Package weight should be between 0 and 100"));
    }

    @Test
    @DisplayName("Should have error if negative weight")
    void shouldHaveErrorIfNegativeWeight() {
        final Package aPackage = PackageFactory.createPackage(-1.92, items);
        final PackageValidator packageValidator = new PackageValidator(messages);
        assertFalse(packageValidator.isValid(aPackage));
        assertTrue(packageValidator.getPackageError().getErrors().contains("Package weight should be between 0 and 100"));
    }

    @Test
    @DisplayName("Should have error if empty valid items")
    void shouldHaveErrorIfEmptyValidItems() {
        List<Item> invalidItems = new ArrayList<>();
        invalidItems.add(new Item(1, 101d, 99.32) );
        final Package aPackage = PackageFactory.createPackage(52d, invalidItems);
        final PackageValidator packageValidator = new PackageValidator(messages);
        assertFalse(packageValidator.isValid(aPackage));
        assertTrue(packageValidator.getPackageError().getErrors().contains("1: Item weight should be between 0 and 100"));
    }

    @Test
    @DisplayName("Should have error if number of items is large than allowed")
    void shouldHaveErrorIfInvalidNoOfItems() {
        List<Item> largeNoOfItems = new ArrayList<>();
        IntStream.range(0, 16).forEach(i -> largeNoOfItems.add(new Item(i, 22d, 23d)));
        final Package aPackage = PackageFactory.createPackage(52d, largeNoOfItems);
        final PackageValidator packageValidator = new PackageValidator(messages);
        assertFalse(packageValidator.isValid(aPackage));
        assertTrue(packageValidator.getPackageError().getErrors().contains("Package can hold maximum 15 items"));
    }

}