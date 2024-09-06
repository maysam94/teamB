package com.example.teambbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.util.UUID;

/**
 * User entity representing a user in the system.
 * Includes validation rules according to test expectations.
 *
 * Author: Mays AlTimemy
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotEmpty(message = "First name is required")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters long")
    @Pattern(regexp = "^[\\p{L}]+$", message = "Name may only contain letters.")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters long")
    @Pattern(regexp = "^[\\p{L}]+$", message = "Name may only contain letters.")
    private String lastName;

    @Size(max = 50, message = "Prefixes must be between 0 and 50 characters long")
    private String prefixes;

    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must have at least one digit, one lower case, one upper case, one special character, and must be at least 8 characters long without spaces")
    private String hashedPassword;

    @Transient
    private String rawPassword;

    @Transient
    private String verifyPassword;

    @Enumerated(EnumType.STRING)
    private UserRole role;


    /**
     * Hashes the raw password using the provided password encoder.
     * It's assumed that the password confirmation logic is handled outside the entity.
     * @param passwordEncoder the password encoder to use for hashing the password.
     */
    public void hashPassword(PasswordEncoder passwordEncoder) {
        if (this.rawPassword != null && !this.rawPassword.isEmpty()) {
            this.hashedPassword = passwordEncoder.encode(this.rawPassword);
            this.rawPassword = null;
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }


    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }


    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public UUID getId() {
        return id;
    }
    public String getEmail() {
        return this.email;
    }

    public void setPrefixes(String prefixes) {
        this.prefixes = prefixes;
    }

    public UserRole getRole() {
        return role;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPrefixes() {
        return prefixes;
    }

    public void setId(UUID uuid) {
        this.id = uuid;
    }
}
