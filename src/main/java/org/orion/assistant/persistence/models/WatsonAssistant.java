package org.orion.assistant.persistence.models;

import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.DeleteSessionOptions;
import com.ibm.watson.assistant.v2.model.MessageContext;
import com.ibm.watson.assistant.v2.model.MessageContextGlobal;
import com.ibm.watson.assistant.v2.model.MessageContextGlobalSystem;
import com.ibm.watson.assistant.v2.model.MessageContextSkills;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.SessionResponse;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TODO: multi-assistant (for multilenguage support)
public class WatsonAssistant {
    private static final Logger LOG = LogManager.getLogger(WatsonAssistant.class); 
    private IamAuthenticator authenticator;
    private Assistant assistant;
    private HttpConfigOptions configOptions;
    private CreateSessionOptions createSessionOptions;
    private SessionResponse session;
    private String assistantId;
    private static String defaultUrl = "https://api.us-south.assistant.watson.cloud.ibm.com"; 
    private static String defaultVersion = "2020-04-01";

    public WatsonAssistant(String apiKey, String id) {
        this(apiKey, defaultVersion, id);
    }
    public WatsonAssistant(String apiKey, String version, String id) {
        this(apiKey, version, defaultUrl, id);
    }
    public WatsonAssistant(String apiKey, String version, String serviceUrl, String id) {
        LOG.info("Creating Watson Assistant instance: " + id + " with version: " + version + " and serviceUrl: " + serviceUrl);
        authenticator = new IamAuthenticator.Builder()
            .apikey(apiKey)
            .build();

        assistant = new Assistant(version, authenticator);
        assistant.setServiceUrl(serviceUrl);

        configOptions = new HttpConfigOptions.Builder()
            .disableSslVerification(true)
            .build();
        assistant.configureClient(configOptions);
        this.assistantId = id;
        LOG.info("Watson Assistant instance created, initializing session...");
        this.createWorkspaceId();
        LOG.info("Session initialized");
    }

    public String createWorkspaceId (){
        //? new CreateSessionOptions.Builder("{environment_id}").build();
        this.createSessionOptions = new CreateSessionOptions.Builder(assistantId).build();
        this.session = assistant.createSession(createSessionOptions).execute().getResult();
        return session.getSessionId();
    }

    public MessageResponse sendMessage(String message, User user) {
        LOG.info("User: "+user.getId()+"|"+user.getName()+"\nSending message: " + message);
        MessageInput input = new MessageInput.Builder()
            .text(message)
            .build();
        MessageOptions options = new MessageOptions.Builder()
            .assistantId(assistantId)
            .sessionId(user.getSessionId())
            .input(input)
            .build();
        return assistant.message(options).execute().getResult();
    }

    public MessageResponse setContextParams(Map<String, Object> contextParams, String workspaceId, String message) {
        MessageInput input = new MessageInput.Builder()
            .text("Hello")
            .build();
        
        MessageContextGlobalSystem systemContext = new MessageContextGlobalSystem.Builder()
            //.userId("user_123")
            .build();

        MessageContextGlobal globalContext = new MessageContextGlobal.Builder()
            .system(systemContext)
            .build();
        //TODO: PORQUE ESTO NO FUNCIONA
        MessageContextSkills skills = new MessageContextSkills.Builder()
            //.add("main_skill", contextParams) //! Use the correct skill name here
            .build();

        MessageContext messageContext = new MessageContext.Builder()
            .global(globalContext)
            .skills(skills)
            .build();

        MessageOptions options = new MessageOptions.Builder("{assistant_id}", session.getSessionId())
            .input(input)
            .context(messageContext)
            .build();

        return assistant.message(options).execute().getResult();
    }

    public void deleteWorkspaceId(String workspaceId) {
        //assistant.deleteSession(session.getSessionId()).execute();
    }
    public String createSession() {
        CreateSessionOptions options = new CreateSessionOptions.Builder(this.assistantId).build();
        SessionResponse response = assistant.createSession(options).execute().getResult();
        return response.getSessionId();
    }
    public void deleteSession(String sessionId) {
        // TODO Auto-generated method stub
        DeleteSessionOptions deleteOptions = new DeleteSessionOptions.Builder(this.assistantId, sessionId).build();
        assistant.deleteSession(deleteOptions).execute();
    }

    
        
}
