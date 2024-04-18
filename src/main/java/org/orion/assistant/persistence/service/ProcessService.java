package org.orion.assistant.persistence.service;

import org.orion.assistant.persistence.models.User;

public interface ProcessService {
    void process(User userDTO);
    
}
