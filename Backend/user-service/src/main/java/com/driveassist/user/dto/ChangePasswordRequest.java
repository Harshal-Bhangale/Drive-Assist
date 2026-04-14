package com.driveassist.user.dto;

import jakarta.validation.constraints.Size;

public class ChangePasswordRequest {

    @Size(min = 6, message = "Current password is required")
    private String currentPassword;

    @Size(min = 6, message = "New password must be at least 6 characters")
    private String newPassword;

    // --- Getters & Setters ---

    public String getCurrentPassword() { return currentPassword; }

    public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }

    public String getNewPassword() { return newPassword; }

    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}
