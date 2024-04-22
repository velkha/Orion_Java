package org.orion.assistant.persistence.service;

import org.orion.assistant.persistence.models.User;

public interface ProcessService {
    String process(User userDTO, String message);
    String process(User userDTO, String session, String message);
    
}
