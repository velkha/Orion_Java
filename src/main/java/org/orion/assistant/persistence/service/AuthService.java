package org.orion.assistant.persistence.service;

import org.orion.assistant.persistence.dao.auth.AuthResponse;
import org.orion.assistant.persistence.dao.auth.SignInReq;
import org.orion.assistant.persistence.dao.auth.SignUpReq;

/**
 * Service interface for the authentication process
 */
public interface AuthService {

    /**
     * Sign up a new user
     * @param request
     * @return
     */
    AuthResponse signUp(SignUpReq request);

    /**
     * Sign in an existing user
     * @param request
     * @return
     */
    AuthResponse signIn(SignInReq request);
}