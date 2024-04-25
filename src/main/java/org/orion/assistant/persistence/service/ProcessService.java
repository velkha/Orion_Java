package org.orion.assistant.persistence.service;

import org.orion.assistant.persistence.dto.UserDTO;

/**
 * Service interface for the process of the messages sent by the user and the chatbot response
 */
public interface ProcessService {

    /**
     * Procesa el mensaje enviado por el usuario a traves del chatbot y retorna la respuesta de este
     * @param userDTO
     * @param message
     * @return
     */
    String process(UserDTO userDTO, String message);

    /**
     * Procesa el mensaje enviado por el usuario a traves del chatbot y retorna la respuesta de este
     * @param userDTO
     * @param session
     * @param message
     * @return
     */
    String process(UserDTO userDTO, String session, String message);
    
}
