package com.example.teambbackend.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for user registration.
 * Includes fields for email, first name, last name, prefixes, password, and password verification.
 * Author: Mays AlTimemy
 */
public class UserDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 320, message = "Email too big")
    private String email;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name too big")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name too big")
    private String lastName;

    @Size(max = 50, message = "Prefixes too big")
    private String prefixes;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 255, message = "Password too big")
    @Pattern(regexp = ".*[!@#$%^&*()_+{}\\[\\]:;<>,.?~\\\\/-].*", message = "Password needs at least one special character")
    private String password;

    @NotBlank(message = "Password verification is required")
    @Size(max = 255, message = "Password verification too big")
    private String verifyPassword;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPrefixes() {
        return prefixes;
    }
    public void setPrefixes(String prefixes) {
        this.prefixes = prefixes;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }
    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public boolean isPasswordMatching() {
        return this.password != null && this.password.equals(this.verifyPassword);
    }
}
