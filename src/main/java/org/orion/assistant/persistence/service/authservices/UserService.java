package org.orion.assistant.persistence.service.authservices;

import java.util.List;

import org.orion.assistant.persistence.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * Interface for the User Service
 */
public interface UserService {
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user);
    void deleteUser(Long id);
    UserDTO getUserByEmail(String email);
    UserDTO getUserBySessionId(String sessionId);
    List<UserDTO> getAllUsers();
    UserDetailsService userDetailsService();

}
