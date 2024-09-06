package com.example.teambbackend.controller.exception;

/**
 * Exception thrown when an attempt is made to create a user that already exists in the system.
 * Extends RuntimeException for unchecked exception handling.
 * This is thrown when an existing email is used to register a new user.
 * Author: Mays AlTimemy
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
