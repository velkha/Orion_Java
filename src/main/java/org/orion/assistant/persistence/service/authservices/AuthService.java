package org.orion.assistant.persistence.service.authservices;

import org.orion.assistant.exception.custom.bbdd.IncorrectPasswordException;
import org.orion.assistant.exception.custom.bbdd.InvalidDataException;
import org.orion.assistant.exception.custom.bbdd.UserAlreadyExistException;
import org.orion.assistant.exception.custom.bbdd.UserNotFoundException;
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
     * @throws InvalidDataException 
     */
    AuthResponse signUp(SignUpReq request) throws UserAlreadyExistException, InvalidDataException;

    /**
     * Sign in an existing user
     * @param request
     * @return
     * @throws UserNotFoundException 
     * @throws IncorrectPasswordException 
     * @throws InvalidDataException 
     */
    AuthResponse signIn(SignInReq request) throws UserNotFoundException, IncorrectPasswordException, InvalidDataException;
}