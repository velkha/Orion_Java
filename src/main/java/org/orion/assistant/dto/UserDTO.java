package org.orion.assistant.dto;

public class UserDTO {
    private long id;
    private String name;
    private String email;
    private String sessionId;
    private String role;

    public UserDTO() {
        
    }

    public UserDTO(String name, String email, String sessionId, String role) {
        this.name = name;
        this.email = email;
        this.sessionId = sessionId;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
