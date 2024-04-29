package org.orion.assistant.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.orion.assistant.persistence.dto.UserDTO;
import org.orion.assistant.persistence.service.authservices.UserService;
import org.orion.assistant.persistence.service.watson.ProcessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * The `AssistantController` class in Java is a REST controller that interacts with services to process
 * user messages with the assistant and manage user sessions.
 */
@RestController
public class AssistantController {

    private ProcessService processService;
    private UserService userService;
    private static final Logger LOG = LogManager.getLogger(AssistantController.class);
    private static String testSession;
    
    // This constructor in the `AssistantController` class is initializing the `processService` and
    // `userService` dependencies using dependency injection. It also loads a properties file named
    // `temporal.properties` from the `src/main/resources` directory and retrieves the value of the
    // property named "session" from it, storing it in the `testSession` variable. If an error occurs
    // during the file loading process, it logs an error message using the Log4j logger.
    public AssistantController(ProcessService processService, UserService userService) {
        this.processService = processService;
        this.userService = userService;
        Properties properties = new Properties();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream("src/main/resources/temporal.properties");
            properties.load(fileInputStream);
            testSession = properties.getProperty("session");
        } catch (IOException e) {
            LOG.error("Error loading Temporal properties file", e);
        }
    }

    /**
     * Test API
     * @param message
     * @return
     */
    //TODO: change to POST & return type to JSON or HTTPResponse like
    @GetMapping("/test")
    public String test(@RequestParam("message") String message) {
        /**
         * 1. Recreate the user
         * 2. Check if the user have an active sesion
         * 3. If not, create a new session
         * 4. Send the message to the Watson Assistant
         * 5. Return the response
         */

        return processService.process(createTemporalTestUser(), testSession, message);
    }
    @GetMapping("/testBBDD")
    public String testBBDD(@RequestParam("message") String message) {
        /**
         * 1. Recreate the user
         * 2. Check if the user have an active sesion
         * 3. If not, create a new session
         * 4. Send the message to the Watson Assistant
         * 5. Return the response
         */
        userService.createUser(createTemporalTestUser());
        userService.getAllUsers().forEach(user -> LOG.info(user.getUsername()));
        LOG.info(userService.getUserById(1L).getUsername());
        return "finished";
    }
    @PostMapping("/restartSession")
    public String restartSession() {
        /**
         * 1. Recreate the user
         * 2. Assing a new session
         */
        return "Session restarted";
    }

    public UserDTO createTemporalTestUser() {
        return new UserDTO("name", "mail", null, "none", "pass");
    }
}
