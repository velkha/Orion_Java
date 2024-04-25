package org.orion.assistant.persistence.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    /**
     * Extract the username from the token
     * @param token
     * @return
     */
    String extractUserName(String token);

    /**
     * Generate a token for the user
     * @param userDetails
     * @return
     */
    String generateToken(UserDetails userDetails);

    /**
     * Validate the token
     * @param token
     * @param userDetails
     * @return
     */
    boolean isTokenValid(String token, UserDetails userDetails);
}
