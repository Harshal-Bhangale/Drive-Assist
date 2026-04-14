package com.driveassist.user.service;

import com.driveassist.common.dto.PagedResponse;
import com.driveassist.common.exception.*;
import com.driveassist.user.dto.*;
import com.driveassist.user.entity.User;
import com.driveassist.user.repository.UserRepository;
import com.driveassist.user.security.JwtService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Concrete implementation of UserService — OOP Polymorphism.
 * Service layer encapsulates business logic away from controller.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public UserResponse create(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("User", "email", request.getEmail());
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("User", "username", request.getUsername());
        }

        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());

        return toResponse(userRepository.save(user));
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = findByIdentifier(request.getIdentifier());
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getId(), user.getUsername(), user.getRole().name());
        return new LoginResponse(token, toResponse(user));
    }

    @Override
    public UserResponse getById(Long id) {
        return toResponse(findUserById(id));
    }

    @Override
    public PagedResponse<UserResponse> getAll(int page, int size, String sortBy) {
        Page<User> userPage = userRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy))
        );
        return new PagedResponse<>(
                userPage.getContent().stream().map(this::toResponse).toList(),
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                userPage.isLast()
        );
    }

    @Override
    @Transactional
    public UserResponse update(Long id, RegisterRequest request) {
        User user = findUserById(id);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPhone(request.getPhone());
        return toResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponse updateProfile(Long id, UpdateUserRequest request) {
        User user = findUserById(id);
        user.setName(request.getName());
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        user.setPhone(request.getPhone());
        if (request.getPreferences() != null) {
            user.setPreferences(request.getPreferences());
        }
        return toResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public void changePassword(Long id, ChangePasswordRequest request) {
        User user = findUserById(id);
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPasswordHash())) {
            throw new UnauthorizedException("Current password is incorrect");
        }
        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updatePreferences(Long id, String preferences) {
        User user = findUserById(id);
        user.setPreferences(preferences);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }

    // --- Private helpers ---

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    private User findByIdentifier(String identifier) {
        if (identifier.contains("@")) {
            return userRepository.findByEmail(identifier)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "email", identifier));
        }
        return userRepository.findByUsername(identifier)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", identifier));
    }

    private UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());
        response.setPreferences(user.getPreferences());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}
