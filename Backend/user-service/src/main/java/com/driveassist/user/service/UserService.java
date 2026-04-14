package com.driveassist.user.service;

import com.driveassist.common.dto.PagedResponse;
import com.driveassist.common.service.CrudService;
import com.driveassist.user.dto.*;

/**
 * User service interface — extends CrudService for standard CRUD (OOP Inheritance).
 * Adds authentication-specific methods.
 */
public interface UserService extends CrudService<RegisterRequest, UserResponse, Long> {

    LoginResponse login(LoginRequest request);

    UserResponse updateProfile(Long id, UpdateUserRequest request);

    void changePassword(Long id, ChangePasswordRequest request);

    void updatePreferences(Long id, String preferences);
}
