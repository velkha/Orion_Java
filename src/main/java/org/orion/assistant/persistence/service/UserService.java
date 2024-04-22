package org.orion.assistant.persistence.service;

import java.util.List;

import org.orion.assistant.persistence.dto.UserDTO;
import org.orion.assistant.persistence.model.User;

public interface UserService {
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user);
    void deleteUser(Long id);
    UserDTO getUserByEmail(String email);
    UserDTO getUserBySessionId(String sessionId);
    List<UserDTO> getAllUsers();

}
