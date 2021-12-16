package com.unite.challenge.converter;

import com.unite.challenge.entity.EmptyPackage;
import com.unite.challenge.entity.Package;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PackageConverterTest {

    @Test
    @DisplayName("Should convert to package correctly")
    void shouldConvertToPackageCorrectly() {
        String line = "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)";
        Package aPackage = PackageConverter.convert(line);
        assertEquals(56.00, aPackage.getWeight());
        assertEquals(9, aPackage.getItems().size());
    }

    @Test
    @DisplayName("Should convert to empty package when format is not matched")
    void shouldConvertToEmptyPackageIfWeightCouldNotBeParsed() {
        String line = "=> [1,90.72,€13] [2,33.80,€40]";
        Package aPackage = PackageConverter.convert(line);
        assertEquals(EmptyPackage.class, aPackage.getClass());
    }

    @Test
    @DisplayName("Should convert to empty package when items could not be formatted")
    void shouldConvertToEmptyPackageWhenItemsInvalidFormat() {
        String line = "56 => [1,90.72,€13] [2,33.80,$40]";
        Package aPackage = PackageConverter.convert(line);
        assertEquals(EmptyPackage.class, aPackage.getClass());
    }

    @Test
    @DisplayName("Should ignore item when item format is invalid")
    void shouldIgnoreItemWhenItemFormatInvalid() {
        String line = "56 : (1,90.72,€13) (2,33.80,$40)";
        Package aPackage = PackageConverter.convert(line);
        assertEquals(1, aPackage.getItems().size());
    }

}