package com.unite.challenge.validator;

import com.unite.challenge.entity.Package;
import com.unite.challenge.entity.dto.PackageError;
import com.unite.challenge.util.Constants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;
import java.util.Properties;

/**
 * Validator for Package entity
 */
@RequiredArgsConstructor
public class PackageValidator {

    @Getter
    private final PackageError packageError = new PackageError();
    private final Properties messages;

    /**
     * <pre>
     * Validates Package object
     * 1. Validates package weight
     * 2. Validates package items
     * 2.1. Validates number of items
     * 2.2. Validates Item objects
     * </pre>
     *
     * @param aPackage Package object to be validated
     * @return true if Package object is valid, false otherwise
     */
    public boolean isValid(Package aPackage) {
        validatePackageWeight(aPackage);
        validatePackageItems(aPackage);
        return !packageError.hasErrors();
    }

    /**
     * Validates package weight
     *
     * @param aPackage Package object
     */
    private void validatePackageWeight(Package aPackage) {
        if (!isValidPackageWeight(aPackage)) {
            packageError.addError(
                    MessageFormat.format(
                            messages.getProperty("package.validation.weight.invalid"),
                            Constants.MIN_PACKAGE_WEIGHT,
                            Constants.MAX_PACKAGE_WEIGHT
                    )
            );
        }
    }

    /**
     * Wrapper method to validate package items
     * 1. Validates number of items
     * 2. Validates Item objects
     *
     * @param aPackage Package object
     */
    private void validatePackageItems(Package aPackage) {
        validateNoOfItemsInPackage(aPackage);
        validateItems(aPackage);
    }

    /**
     * Validates number of items in a package
     *
     * @param aPackage Package object
     */
    private void validateNoOfItemsInPackage(Package aPackage) {
        if (!isValidNoOfItemsInPackage(aPackage)) {
            packageError.addError(
                    MessageFormat.format(
                            messages.getProperty("package.validation.noOfItems.invalid"),
                            Constants.MAX_ITEMS_IN_PACKAGE
                    )
            );
        }
    }

    /**
     * Validates items in a package
     *
     * @param aPackage Package object
     */
    private void validateItems(Package aPackage) {
        aPackage.getItems().forEach(item -> {
            ItemValidator itemValidator = new ItemValidator(messages);
            if (!itemValidator.isValid(item)) {
                packageError.addErrors(itemValidator.getErrors());
            }
        });
    }

    /**
     * Checks if the package weight is within allowed range
     *
     * @param aPackage Package object
     * @return true if package weight is within range, false otherwise
     */
    private boolean isValidPackageWeight(Package aPackage) {
        return aPackage.getWeight() >= Constants.MIN_PACKAGE_WEIGHT
                && aPackage.getWeight() <= Constants.MAX_PACKAGE_WEIGHT;
    }

    /**
     * Checks number of items in a package
     *
     * @param aPackage Package object
     * @return true if number of items is less than allowed, false otherwise
     */
    private boolean isValidNoOfItemsInPackage(Package aPackage) {
        return aPackage.getItems().size() <= Constants.MAX_ITEMS_IN_PACKAGE;
    }
}
