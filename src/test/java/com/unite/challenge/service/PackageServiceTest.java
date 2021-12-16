package com.unite.challenge.service;

import com.unite.challenge.converter.PackageConverter;
import com.unite.challenge.entity.Package;
import com.unite.challenge.entity.dto.PackageProcessingResult;
import com.unite.challenge.processor.FileReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PackageServiceTest {

    @Test
    @DisplayName("Should process the valid package successfully")
    void shouldProcessValidPackageLine() {
        final String validPackageLine = "75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)";
        final Package aPackage = PackageConverter.convert(validPackageLine);
        PackageService packageService = new PackageService(new FileReader());
        final PackageProcessingResult packageProcessingResult = packageService.processPackage(aPackage);
        assertNotNull(packageProcessingResult);
        assertEquals("2, 7", packageProcessingResult.toString());
    }

    @Test
    @DisplayName("Should process and return empty if no result")
    void shouldReturnDashIfNoResult() {
        final String validPackageLine = "56 : (1,90.72,€13) (5,56.81,€36) (6,98.77,€79) (7,81.80,€45)";
        final Package aPackage = PackageConverter.convert(validPackageLine);
        PackageService packageService = new PackageService(new FileReader());
        final PackageProcessingResult packageProcessingResult = packageService.processPackage(aPackage);
        assertNotNull(packageProcessingResult);
        assertEquals("-", packageProcessingResult.toString());
    }

    @Test
    @DisplayName("Should process and consider lower weight if cost of two items are equal")
    void shouldConsiderItemWithLessWeightIfCostsAreEqual() {
        final String validPackageLine = "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)";
        final Package aPackage = PackageConverter.convert(validPackageLine);
        PackageService packageService = new PackageService(new FileReader());
        final PackageProcessingResult packageProcessingResult = packageService.processPackage(aPackage);
        assertNotNull(packageProcessingResult);
        assertFalse(packageProcessingResult.getResultIndexes().contains(6));
        assertEquals("8, 9", packageProcessingResult.toString());
    }

}