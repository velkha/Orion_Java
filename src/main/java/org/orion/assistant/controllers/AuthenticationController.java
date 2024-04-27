package org.orion.assistant.controllers;

import org.orion.assistant.exception.custom.bbdd.UserAlreadyExistException;
import org.orion.assistant.persistence.dao.auth.AuthResponse;
import org.orion.assistant.persistence.dao.auth.SignInReq;
import org.orion.assistant.persistence.dao.auth.SignUpReq;
import org.orion.assistant.persistence.service.authservices.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private static Logger LOG = LogManager.getLogger(AuthenticationController.class);
    //TODO: pasarlo a constructor
    @Autowired
    private  AuthService authenticationService;

    /**
     * Petitition to sign up, request body must contain the user data, it save the user in the database
     * username, password, email
     * @param request - SignUpReq object
     * @return token of authentication or a message if the user already exists
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpReq request) {
        LOG.info("-----------------------------------------------------------");
        LOG.info("Request received: {}", request); 
        LOG.info("-----------------------------------------------------------");
        try {
            AuthResponse response = authenticationService.signUp(request);
            return ResponseEntity.ok(response);
        } catch (UserAlreadyExistException e) {
            // Return a 409 status code with a custom error message
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
    }

    /**
     *  Petitition to sign in, request body must contain the user data
     * username, password
     * @param request - SignInReq object
     * @return //TODO: QUE RETORNA
     */
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody SignInReq request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }

}