package com.example.teambbackend.controller;

import com.example.teambbackend.controller.dto.AssignmentDTO;
import com.example.teambbackend.model.Assignment;
import com.example.teambbackend.model.User;
import com.example.teambbackend.model.UserRole;
import com.example.teambbackend.service.AssignmentService;
import com.example.teambbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/assignments")
@Validated
public class AssignmentController {
    private final AssignmentService assignmentService;
    private final UserService userService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService, UserService userService) {
        this.assignmentService = assignmentService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createAssignment(@RequestBody @Valid AssignmentDTO assignmentDTO,
                                              @CookieValue(value = "sessionId", required = false) String sessionId) {
        if (sessionId == null || sessionId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userService.findBySessionId(sessionId);
        if (user == null || !UserRole.TEACHER.equals(user.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (assignmentDTO.getTitle() == null || assignmentDTO.getTitle().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Assignment assignment = assignmentService.convertToAssignment(assignmentDTO, user);
        assignmentService.createAssignment(assignment);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Assignment successfully created"));
    }
}