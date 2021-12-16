package com.unite.challenge.entity.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper for errors found while processing the package
 */
public class PackageError {
    @Getter
    private final List<String> errors = new ArrayList<>();

    public void addError(String errorMessage) {
        errors.add(errorMessage);
    }

    public void addErrors(List<String> errorMessages) {
        errors.addAll(errorMessages);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    @Override
    public String toString() {
        return "PackageError{" +
                "errors=" + errors +
                '}';
    }
}
