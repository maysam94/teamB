package com.example.teambbackend.controller.exception;

/**
 * Custom exception for handling cases where a requested user cannot be found in the system.
 * Extends RuntimeException for unchecked exception handling.
 * This is thrown when a user operation is performed on a non-existent user.
 * Author: Mays AlTimemy
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
