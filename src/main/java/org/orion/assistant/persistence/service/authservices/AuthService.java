package org.orion.assistant.persistence.service.authservices;

import org.orion.assistant.exception.custom.bbdd.UserAlreadyExistException;
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
     * @throws UserAlreadyExistException 
     */
    AuthResponse signUp(SignUpReq request) throws UserAlreadyExistException;

    /**
     * Sign in an existing user
     * @param request
     * @return
     */
    AuthResponse signIn(SignInReq request);
}