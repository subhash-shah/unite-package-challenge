package com.unite.challenge.factory;

import com.unite.challenge.entity.EmptyPackage;
import com.unite.challenge.entity.Item;
import com.unite.challenge.entity.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PackageFactoryTest {

    List<Item> items = new ArrayList<>();

    @BeforeEach
    void initialize() {
        Item firstItem = new Item(1, 52.98, 22.98);
        Item secondItem = new Item(2, 22.45, 45.23);
        Item thirdItem = new Item(3, 49.44, 87.43);
        items.add(firstItem);
        items.add(secondItem);
        items.add(thirdItem);
    }

    @Test
    @DisplayName("Should create Package object")
    void shouldCreatePackage() {
        final Package aPackage = PackageFactory.createPackage(88.34, items);
        assertEquals(Package.class, aPackage.getClass());
    }

    @Test
    @DisplayName("Should create EmptyPackage object if invalid weight")
    void shouldCreateEmptyPackageIfInvalidWeight() {
        final Package aPackage = PackageFactory.createPackage(-10.0, items);
        assertEquals(EmptyPackage.class, aPackage.getClass());
    }

    @Test
    @DisplayName("Should create EmptyPackage object if empty items")
    void shouldCreateEmptyPackageIfEmptyItems() {
        final Package aPackage = PackageFactory.createPackage(15.89, Collections.emptyList());
        assertEquals(EmptyPackage.class, aPackage.getClass());
    }

}