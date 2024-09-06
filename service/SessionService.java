package com.example.teambbackend.service;

import com.example.teambbackend.controller.dto.AuthenticationDTO;
import com.example.teambbackend.controller.exception.AuthenticationException;
import com.example.teambbackend.controller.exception.UserValidationException;
import com.example.teambbackend.model.Session;
import com.example.teambbackend.model.User;
import com.example.teambbackend.repository.SessionRepository;
import com.example.teambbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Service class for handling user session-related operations.
 * It provides functionality to authenticate a user and create a session.
 * Author: Mays Altimemy
 */
@Service
public class SessionService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(UserRepo userRepo, PasswordEncoder passwordEncoder, SessionRepository sessionRepository) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.sessionRepository = sessionRepository;
    }

    /**
     * Authenticates the user based on email and password.
     * If successful, creates and returns a session ID.
     *
     * @param authenticationDTO Contains the email and password for authentication.
     * @return The session ID as a string if authentication is successful.
     * @throws AuthenticationException If the user cannot be authenticated.
     * @throws UserValidationException If email or password validations fail.
     */
    public String authenticateAndCreateSession(AuthenticationDTO authenticationDTO) {
        String email = authenticationDTO.email();
        String password = authenticationDTO.password();

        validateEmailAndPassword(email, password);

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("User not found with the provided email"));

        if (!passwordEncoder.matches(password, user.getHashedPassword())) {
            throw new AuthenticationException("Invalid password");
        }

        Session session = createNewSession(user.getId());
        return session.getSessionId().toString();
    }

    /**
     * Validates the email and password for being non-null, non-empty, and properly formatted.
     *
     * @param email    The email to validate.
     * @param password The password to validate.
     * @throws UserValidationException If the email or password is empty or the email format is invalid.
     */
    private void validateEmailAndPassword(String email, String password) {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new UserValidationException("Email and password cannot be empty");
        }

        if (!isValidEmail(email)) {
            throw new UserValidationException("Invalid email format");
        }
    }

    /**
     * Checks if the email is in a valid format.
     *
     * @param email The email string to validate.
     * @return True if the email is in a valid format, false otherwise.
     */
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    /**
     * Creates a new session for a given user ID and persists it.
     *
     * @param userId The unique identifier of the user.
     * @return The newly created session object.
     * @throws IllegalArgumentException If the user ID is null or the user is not found.
     */
    private Session createNewSession(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Session session = new Session();
        session.setUser(user);
        session.setCreatedAt(LocalDateTime.now());
        session.setUpdatedAt(LocalDateTime.now());
        session.setSessionId(UUID.randomUUID());

        sessionRepository.save(session);
        return session;
    }

    public boolean isUserAuthorized(String sessionId, String requiredRole) throws AuthenticationException {
        Optional<Session> sessionOpt = sessionRepository.findBySessionId(UUID.fromString(sessionId));
        if (!sessionOpt.isPresent()) {
            throw new AuthenticationException("Session not found");
        }

        Session session = sessionOpt.get();
        User user = session.getUser();

        if (user == null || !user.getRole().equals(requiredRole)) {
            throw new AuthenticationException("User not authorized for the requested operation");
        }

        return true;
    }
}
