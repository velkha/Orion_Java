package org.orion.assistant.persistence.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.orion.assistant.exception.custom.ResourceNotFoundException;
import org.orion.assistant.integration.WatsonAssistant;
import org.orion.assistant.persistence.dto.UserDTO;
import org.orion.assistant.persistence.service.ProcessService;
import org.springframework.stereotype.Service;

@Service
public class ProcessServiceImpl implements ProcessService{
    private static final Logger LOG = LogManager.getLogger(ProcessServiceImpl.class); 
    private WatsonAssistant watsonAssistant;

    public ProcessServiceImpl() throws ResourceNotFoundException {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/watson/watson.properties");
            properties.load(fileInputStream);
            
            String apiKey = properties.getProperty("watson.apiKey");
            String url = properties.getProperty("watson.assistant.url");
            String version = properties.getProperty("watson.assistant.version");
            String assistantId = properties.getProperty("watson.assistant.id");
            watsonAssistant = new WatsonAssistant(apiKey, version,  url, assistantId);
            
            fileInputStream.close();
        } catch (IOException e) {
            LOG.error("Error loading Watson properties file", e);
            //throw new ResourceNotFoundException("File not found");
        }
    }
    
    public ProcessServiceImpl(WatsonAssistant watsonAssistant) {
        this.watsonAssistant = watsonAssistant;
    }
    public String process(UserDTO user, String message) {
        return this.process(user, null, message);
    }
    public String process(UserDTO user, String session, String message) {
        if (session != null) {
            user.setSessionId(session);
        }
        else {
            user.setSessionId(watsonAssistant.createSession());
        }
        LOG.info("Processing user: " );
        LOG.info("User name: " + user.getName());
        LOG.info("User mail: " + user.getEmail());
        LOG.info("User sessionId: " + user.getSessionId());
        LOG.info("User role: " + user.getRole());
        
        LOG.info("Watson Assistant: " + watsonAssistant);
        String response = watsonAssistant.sendMessage(message, user).toString();
        LOG.info("Response: " + response);
        return response;
    }
}
