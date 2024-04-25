package org.orion.assistant.persistence.dto;

public class UserDTO {
    private long id;
    private String username;
    private String email;
    private String sessionId;
    private String role;
    private String password;
    public UserDTO() {
        
    }

    public UserDTO(String name, String email, String sessionId, String role, String password) {
        this.username = name;
        this.email = email;
        this.sessionId = sessionId;
        this.role = role;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
