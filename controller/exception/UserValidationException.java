package com.example.teambbackend.controller.exception;

/**
 * Custom exception for handling validation errors related to user operations.
 * This class extends RuntimeException and is used to indicate issues with user data
 * validation such as format errors, incomplete data, or other validation failures.
 * Author: Mays AlTimemy
 */
public class UserValidationException extends RuntimeException {

    /**
     * Constructor for UserValidationException.
     * @param message A descriptive message explaining the nature of the validation error.
     */

    public UserValidationException(String message) {
        super(message);
    }
}
