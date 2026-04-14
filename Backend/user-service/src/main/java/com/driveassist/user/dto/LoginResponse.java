package com.driveassist.user.dto;

/**
 * Login response containing JWT token and user details.
 */
public class LoginResponse {

    private String token;
    private String tokenType = "Bearer";
    private UserResponse user;

    public LoginResponse(String token, UserResponse user) {
        this.token = token;
        this.user = user;
    }

    // --- Getters ---

    public String getToken() { return token; }

    public String getTokenType() { return tokenType; }

    public UserResponse getUser() { return user; }
}
