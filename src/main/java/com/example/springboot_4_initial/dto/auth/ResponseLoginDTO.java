package com.example.springboot_4_initial.dto.auth;

public class ResponseLoginDTO {
    private String token;
    private boolean isAdmin;

    public ResponseLoginDTO(String token, boolean isAdmin) {
        this.token = token;
        this.isAdmin = isAdmin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
