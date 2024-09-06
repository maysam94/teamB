package com.example.teambbackend.controller.dto;

import com.example.teambbackend.util.annotation.dataFormat.ValidDateFormat;
import com.example.teambbackend.util.annotation.future.FutureDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class AssignmentDTO {

    private Integer id;

    @NotBlank(message = "Assignment title is required")
    @Size(min = 1, max = 250, message = "Assignment title must be between 1 and 250 characters")
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String teacherCheatSheet;
    @FutureDate
    @ValidDateFormat
    private String deadline;

    public AssignmentDTO() {
    }


    public AssignmentDTO(Integer id, String title, String description, String teacherCheatSheet, String deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.teacherCheatSheet = teacherCheatSheet;
        this.deadline = deadline;
    }


    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTeacherCheatSheet() {
        return teacherCheatSheet;
    }

    public String getDeadline() {
        return deadline;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTeacherCheatSheet(String teacherCheatSheet) {
        this.teacherCheatSheet = teacherCheatSheet;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    private UUID createdBy;

    // Getter for createdBy
    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

}
