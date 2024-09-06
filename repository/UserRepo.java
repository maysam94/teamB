package com.example.teambbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.teambbackend.model.User;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    void deleteById(UUID id);
    boolean existsByEmail(String email);
    Optional<User> findById(UUID id);

    Optional<User> findByEmail(String email);

}
