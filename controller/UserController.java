package com.example.teambbackend.controller;

import com.example.teambbackend.controller.dto.UserDTO;
import com.example.teambbackend.controller.exception.UserAlreadyExistsException;
import com.example.teambbackend.controller.exception.UserValidationException;
import com.example.teambbackend.model.User;
import com.example.teambbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/students")
    public ResponseEntity<Map<String, String>> registerStudent(@Valid @RequestBody UserDTO registrationDTO) {
        try {
            User registeredUser = userService.registerUser(registrationDTO);
            return new ResponseEntity<>(Map.of("message", "User has been created"), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(Map.of("message", "User already exists"), HttpStatus.CONFLICT);
        } catch (UserValidationException e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "An unexpected error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/students")
    public ResponseEntity<Map<String, String>> getAllStudents() {
        return new ResponseEntity<>(Map.of("message", "Retrieve all students (not implemented yet)"), HttpStatus.OK);
    }
}
