package com.example.teambbackend.service;

import com.example.teambbackend.controller.dto.AssignmentDTO;
import com.example.teambbackend.model.Assignment;
import com.example.teambbackend.model.User;
import com.example.teambbackend.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public Assignment convertToAssignment(AssignmentDTO assignmentDTO, User user) {
        Assignment assignment = new Assignment();
        assignment.setTitle(assignmentDTO.getTitle());
        assignment.setDescription(assignmentDTO.getDescription());
        assignment.setTeacherCheatSheet(assignmentDTO.getTeacherCheatSheet());
        assignment.setCreatedBy(user);

        if (assignmentDTO.getDeadline() != null && !assignmentDTO.getDeadline().isEmpty()) {
            assignment.setDeadline(LocalDate.parse(assignmentDTO.getDeadline(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }

        return assignment;
    }

    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }
}
