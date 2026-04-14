package com.driveassist.user.dto;

import com.driveassist.user.enums.Role;
import java.time.Instant;

/**
 * User response DTO — encapsulates User entity data for API responses.
 * Never exposes passwordHash (Encapsulation).
 */
public class UserResponse {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private Role role;
    private String preferences;
    private Instant createdAt;

    // --- Getters & Setters ---

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public String getPreferences() { return preferences; }

    public void setPreferences(String preferences) { this.preferences = preferences; }

    public Instant getCreatedAt() { return createdAt; }

    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
