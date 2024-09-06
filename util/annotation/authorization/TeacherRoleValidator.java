package com.example.teambbackend.util.annotation.authorization;


import com.example.teambbackend.model.User;
import com.example.teambbackend.model.UserRole;
import org.springframework.stereotype.Component;

@Component
public class TeacherRoleValidator {
    public boolean validate(User user) {
        return user.getRole().equals(UserRole.TEACHER);
    }
}

