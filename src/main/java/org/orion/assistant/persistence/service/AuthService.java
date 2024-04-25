package org.orion.assistant.persistence.service;

import org.orion.assistant.persistence.dao.auth.AuthResponse;
import org.orion.assistant.persistence.dao.auth.SignInReq;
import org.orion.assistant.persistence.dao.auth.SignUpReq;

public interface AuthService {
    AuthResponse signUp(SignUpReq request);

    AuthResponse signIn(SignInReq request);
}