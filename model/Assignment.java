package com.example.teambbackend.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(length = 255)
    private String teacherCheatSheet;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    private LocalDate deadline;

    @CreatedDate
    private LocalDate createdAt;

    public Assignment() {
    }

    public Assignment(String title, String description, String teacherCheatSheet, LocalDate deadline, User createdBy) {
        this.title = title;
        this.description = description;
        this.teacherCheatSheet = teacherCheatSheet;
        this.deadline = deadline;
        this.createdBy = createdBy;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public User getCreatedBy() {
        return createdBy;
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

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
