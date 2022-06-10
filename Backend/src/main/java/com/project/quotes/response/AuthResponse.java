package com.project.quotes.response;

import com.project.quotes.model.UserRole;

public class AuthResponse {
    private final UserRole role;
    private final String accessToken;

    public AuthResponse(UserRole role, String accessToken) {
        this.role = role;
        this.accessToken = accessToken;
    }

    public UserRole getRole() {
        return role;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
