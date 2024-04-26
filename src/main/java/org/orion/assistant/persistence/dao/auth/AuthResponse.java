package org.orion.assistant.persistence.dao.auth;

/**
 * Response object for the JWT authentication process
 * {@link org.orion.assistant.persistence.service.authservices.AuthService}
 */
public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse() {
        //TODO Auto-generated constructor stub
    }

    public static class Builder {
        private AuthResponse response = new AuthResponse();

        public Builder token(String token) {
            response.token = token;
            return this;
        }

        public AuthResponse build() {
            return response;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

