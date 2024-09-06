package com.example.teambbackend.controller;

import com.example.teambbackend.controller.dto.AuthenticationDTO;
import com.example.teambbackend.controller.exception.AuthenticationException;
import com.example.teambbackend.controller.exception.UserValidationException;
import com.example.teambbackend.service.SessionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthenticationDTO authenticationDTO, HttpServletResponse response) {
        try {
            String sessionId = sessionService.authenticateAndCreateSession(authenticationDTO);

            int sessionDurationInSeconds = 3600;

            Cookie sessionCookie = new Cookie("session_id", sessionId);
            sessionCookie.setMaxAge(sessionDurationInSeconds);
            sessionCookie.setHttpOnly(true);
            sessionCookie.setPath("/");
            response.addCookie(sessionCookie);

            return new ResponseEntity<>(Map.of("message", "Successful login"), HttpStatus.OK);
        } catch (UserValidationException e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(Map.of("message", "Email or password are incorrect"), HttpStatus.UNAUTHORIZED);
        }
    }
}
