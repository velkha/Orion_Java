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
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignUpReq request) {
        LOG.info("-----------------------------------------------------------");
        LOG.info("Request received: {}", request); 
        LOG.info("-----------------------------------------------------------");
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody SignInReq request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }

}