package org.orion.assistant.persistence.service;

import org.orion.assistant.persistence.dto.UserDTO;

public interface ProcessService {
    String process(UserDTO userDTO, String message);
    String process(UserDTO userDTO, String session, String message);
    
}
