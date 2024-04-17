package org.orion.assistant.persistence.models;

import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.MessageContext;
import com.ibm.watson.assistant.v2.model.MessageContextGlobal;
import com.ibm.watson.assistant.v2.model.MessageContextGlobalSystem;
import com.ibm.watson.assistant.v2.model.MessageContextSkills;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.SessionResponse;
import java.util.Map;

//TODO: multi-assistant (for multilenguage support)
public class WatsonAssistant {
    private IamAuthenticator authenticator;
    private Assistant assistant;
    private HttpConfigOptions configOptions;
    private CreateSessionOptions createSessionOptions;
    private SessionResponse session;
    private static String defaultUrl = "https://api.us-south.assistant.watson.cloud.ibm.com"; 
    private static String defaultVersion = "2020-04-01";


    public WatsonAssistant(String apiKey) {
        this(apiKey, defaultVersion);
    }
    public WatsonAssistant(String apiKey, String version) {
        this(apiKey, version, defaultUrl);
    }
    public WatsonAssistant(String apiKey, String version, String serviceUrl) {
        authenticator = new IamAuthenticator.Builder()
            .apikey(apiKey)
            .build();

        assistant = new Assistant(version, authenticator);
        assistant.setServiceUrl(serviceUrl);

        configOptions = new HttpConfigOptions.Builder()
            .disableSslVerification(true)
            .build();
        assistant.configureClient(configOptions);
    }

    //TODO: Implement this method
    public Object processMessage(String workspaceId, String message) {
        return null;
    }

    public String createWorkspaceId (){
        //TODO: implement assistant ID in the create session options
        this.createSessionOptions = new CreateSessionOptions.Builder().build();
        this.session = assistant.createSession(createSessionOptions).execute().getResult();
        return session.getSessionId();
    }

    public MessageResponse sendMessage(String workspaceId, String message) {
        MessageInput input = new MessageInput.Builder()
            .text(message)
            .build();
        MessageOptions options = new MessageOptions.Builder()
            .assistantId(workspaceId)
            .sessionId(session.getSessionId())
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
            //.add("main_skill", contextParams) // Use the correct skill name here
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

    
        
}
