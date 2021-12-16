package com.unite.challenge.exception;

/**
 * Custom exception class for wrapping domain errors
 */
public class CustomAppException extends Exception {

    public CustomAppException(String errorMessage) {
        super(errorMessage);
    }
}
