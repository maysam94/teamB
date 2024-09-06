package com.example.teambbackend.util.factory;

import com.example.teambbackend.controller.dto.UserDTO;
import com.example.teambbackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User createUserEntity(UserDTO userDTO, String encodedPassword) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPrefixes(userDTO.getPrefixes());
        user.setEmail(userDTO.getEmail());
        user.setHashedPassword(encodedPassword);
        return user;
    }

    public void updateUserEntity(User user, UserDTO userDTO, String encodedPassword) {
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPrefixes(userDTO.getPrefixes());
        user.setEmail(userDTO.getEmail());
        user.setHashedPassword(encodedPassword);
    }
}
