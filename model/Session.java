package com.example.teambbackend.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID sessionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    /**
     * Gets the session ID.
     * @return UUID representing the unique identifier of the session.
     */
    public UUID getSessionId() {
        return sessionId;
    }

    /**
     * Sets the session ID.
     * @param sessionId A UUID representing the session identifier to be set.
     */
    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Gets the associated user of the session.
     * @return User object linked to this session.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with the session.
     * @param user User object to be associated with this session.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the creation timestamp of the session.
     * @return LocalDateTime representing the time when the session was created.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the session.
     * @param createdAt LocalDateTime representing the time to set as the session's creation time.
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the last updated timestamp of the session.
     * @return LocalDateTime representing the time when the session was last updated.
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the last updated timestamp of the session.
     * @param updatedAt LocalDateTime representing the time to set as the session's last update time.
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Placeholder method for setting the user ID. Currently, it does not perform any action.
     * @param userId UUID of the user to be associated with this session.
     */
    public void setUserId(UUID userId) {
    }
}