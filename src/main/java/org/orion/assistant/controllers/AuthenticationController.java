package org.orion.assistant.controllers;

import org.orion.assistant.persistence.dao.auth.AuthResponse;
import org.orion.assistant.persistence.dao.auth.SignInReq;
import org.orion.assistant.persistence.dao.auth.SignUpReq;
import org.orion.assistant.persistence.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @return token of authentication
     */
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignUpReq request) {
        LOG.info("-----------------------------------------------------------");
        LOG.info("Request received: {}", request); 
        LOG.info("-----------------------------------------------------------");
        //TODO: check so if duplicat it do not thrown the 409(conflict) in server and just return a  with a message that the user already exist 
        //! [DO NOT RETURN THE TOKEN IN CASE OF DUPLICATE USER, JUST RETURN A MESSAGE SAYING THAT THE USER ALREADY EXIST]

        return ResponseEntity.ok(authenticationService.signUp(request));
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