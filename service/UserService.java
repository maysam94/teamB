package com.example.teambbackend.service;

import com.example.teambbackend.controller.dto.UserDTO;
import com.example.teambbackend.controller.exception.UserAlreadyExistsException;
import com.example.teambbackend.controller.exception.UserValidationException;
import com.example.teambbackend.model.User;
import com.example.teambbackend.model.Session;
import com.example.teambbackend.repository.UserRepo;
import com.example.teambbackend.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Service class for user management operations, handling user data validation and persistence.
 * Author: Mays AlTimemy
 */
@Service
@Transactional
public class UserService {
    private final UserRepo userRepo;

    private final SessionRepository sessionRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    /**
     * Autowired constructor to inject UserRepo and PasswordEncoder beans.
     *
     * @param userRepo        The user repository interface.
     * @param passwordEncoder The password encoding utility.
     */
    @Autowired
    public UserService(UserRepo userRepo, SessionRepository sessionRepository, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = passwordEncoder;
        this.logger.info("UserService created");
    }

    /**
     * Registers a new user with the details provided in the UserDTO.
     * Validates user data and encrypts the password before saving to the database.
     *
     * @param registrationDTO Data transfer object containing user registration data.
     * @return User The saved user entity.
     * @throws UserValidationException    If validation fails.
     * @throws UserAlreadyExistsException If the email already exists in the database.
     */
    public User registerUser(UserDTO registrationDTO) throws UserValidationException, UserAlreadyExistsException {
        if (!registrationDTO.getPassword().equals(registrationDTO.getVerifyPassword())) {
            throw new UserValidationException("Passwords do not match.");
        }

        validateEmail(registrationDTO.getEmail());
        validateName(registrationDTO.getFirstName(), "First name may only contain letters and must be between 1 and 50 characters long.");
        validateName(registrationDTO.getLastName(), "Last name may only contain letters and must be between 1 and 50 characters long.");
        validatePassword(registrationDTO.getPassword());

        checkIfUserExists(registrationDTO.getEmail());

        User newUser = createUserEntity(registrationDTO);
        return userRepo.save(newUser);
    }


    /**
     * Validates the provided email address.
     *
     * @param email The email address to validate.
     * @throws UserValidationException If the email format is invalid.
     */
    private void validateEmail(String email) throws UserValidationException {
        if (email == null || !Pattern.compile("^[A-Za-z0-9+_.-]+@[a-zA-Z0-9.-]+$").matcher(email).matches()) {
            throw new UserValidationException("The given email is incorrect");
        }
    }

    /**
     * Validates the provided name.
     *
     * @param name         The name to validate.
     * @param errorMessage The error message to throw if validation fails.
     * @throws UserValidationException If the name format is invalid.
     */
    private void validateName(String name, String errorMessage) throws UserValidationException {
        if (name == null || name.isEmpty() || name.length() > 50 || !name.matches("^[\\p{L}]+$")) {
            throw new UserValidationException(errorMessage);
        }
    }

    /**
     * Validates the provided password.
     *
     * @param password The password to validate.
     * @throws UserValidationException If the password does not meet the criteria.
     */
    private void validatePassword(String password) throws UserValidationException {
        if (password == null || password.length() < 8 || !password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}$")) {
            throw new UserValidationException("The password doesn't meet the required length");
        }
    }

    /**
     * Checks if a user with the given email already exists.
     *
     * @param email The email to check against the database.
     * @throws UserAlreadyExistsException If the email is found in the database.
     */
    private void checkIfUserExists(String email) throws UserAlreadyExistsException {
        if (userRepo.existsByEmail(email)) {
            throw new UserAlreadyExistsException("This email is already taken");
        }
    }

    /**
     * Creates a new User entity from the provided UserDTO.
     *
     * @param registrationDTO The UserDTO containing registration data.
     * @return The newly created User object.
     */
    private User createUserEntity(UserDTO registrationDTO) {
        User newUser = new User();
        newUser.setEmail(registrationDTO.getEmail());
        newUser.setFirstName(registrationDTO.getFirstName());
        newUser.setLastName(registrationDTO.getLastName());
        newUser.setPrefixes(registrationDTO.getPrefixes());
        newUser.setHashedPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        return newUser;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return A List of User entities.
     */
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    public User findBySessionId(String sessionId) {
        return sessionRepository.findBySessionId(UUID.fromString(sessionId))
                .map(Session::getUser)
                .orElse(null);
    }
    public boolean isUserAuthorized(UUID userId, String role) {
        return userRepo.findById(userId)
                .map(User::getRole)
                .filter(userRole -> userRole.toString().equals(role))
                .isPresent();
    }


}

