package com.example.teambbackend.util.factory;

import com.example.teambbackend.controller.dto.AssignmentDTO;
import com.example.teambbackend.model.Assignment;
import com.example.teambbackend.model.User;
import com.example.teambbackend.model.UserRole;
import com.example.teambbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@Component
public class AssignmentFactory {

    private final UserRepo userRepo;

    @Autowired
    public AssignmentFactory(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Assignment createAssignmentEntity(AssignmentDTO dto, UUID creatorId) throws DateTimeParseException, IllegalArgumentException {
        LocalDate deadline = LocalDate.parse(dto.getDeadline(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        User creator = userRepo.findById(creatorId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + creatorId));

        if (!creator.getRole().equals(UserRole.TEACHER.name())) {
            throw new IllegalArgumentException("Only users with role TEACHER can create assignments.");
        }

        Assignment assignment = new Assignment();
        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setTeacherCheatSheet(dto.getTeacherCheatSheet());
        assignment.setDeadline(deadline);
        assignment.setCreatedBy(creator);

        return assignment;
    }

}