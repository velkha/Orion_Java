package org.orion.assistant.persistence.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.orion.assistant.exception.modelexceptions.ResourceNotFoundException;
import org.orion.assistant.persistence.models.User;
import org.orion.assistant.persistence.models.WatsonAssistant;
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
            e.printStackTrace();
            //throw new ResourceNotFoundException("File not found");
        }
    }
    
    public ProcessServiceImpl(WatsonAssistant watsonAssistant) {
        this.watsonAssistant = watsonAssistant;
    }
    public void process(User userDTO) {
        LOG.info("Processing user: " );
        LOG.info("User name: " + userDTO.getName());
        LOG.info("User mail: " + userDTO.getEmail());
        LOG.info("User sessionId: " + userDTO.getSessionId());
        LOG.info("User role: " + userDTO.getRole());
        
        LOG.info("Watson Assistant: " + watsonAssistant);
        LOG.info(watsonAssistant.sendMessage(null, "hola"));
    }

}
