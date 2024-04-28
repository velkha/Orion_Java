package org.orion.assistant.persistence.dao.auth;

/**
 * Request object for the sign in process
 * {@link org.orion.assistant.persistence.service.authservices.AuthService#signIn(SignInReq)}
 */
public class SignInReq {
    private String username;
    private String password;

    public SignInReq() {
    }

    public SignInReq(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toLog() {
        return "SignInReq{" +
                "username='" + username + '\'' +
                '}';
    }
}