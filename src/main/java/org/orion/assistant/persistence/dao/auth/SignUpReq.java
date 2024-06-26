package org.orion.assistant.persistence.dao.auth;


/**
 * Request object for the sign up process
 * {@link org.orion.assistant.persistence.service.authservices.AuthService#signIn(SignUpReq)}
 */
public class SignUpReq {
    private String username;
    private String password;
    private String email;

    public SignUpReq() {
    }

    public SignUpReq(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toLog() {
        return "SignUpReq{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
