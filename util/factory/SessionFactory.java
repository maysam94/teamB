package com.example.teambbackend.util.factory;

import com.example.teambbackend.model.Session;
import com.example.teambbackend.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class SessionFactory {

    public Session createSession(User user) {
        Session session = new Session();
        session.setUser(user);
        session.setCreatedAt(LocalDateTime.now());
        session.setUpdatedAt(LocalDateTime.now());
        session.setSessionId(UUID.randomUUID());
        return session;
    }
}
