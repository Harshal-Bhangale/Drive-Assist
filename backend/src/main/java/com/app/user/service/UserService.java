package com.app.user.service;

import com.app.user.dto.LoginDTO;
import com.app.user.dto.UserDTO;
import com.app.user.model.User;
import com.app.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User register(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> login(LoginDTO dto) {
        Optional<User> byEmail = dto.getIdentifier().contains("@")
                ? userRepository.findByEmail(dto.getIdentifier())
                : userRepository.findByUsername(dto.getIdentifier());
        return byEmail.filter(u -> passwordEncoder.matches(dto.getPassword(), u.getPasswordHash()));
    }

    @Transactional
    public void updatePreferences(Long userId, String prefs) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setPreferences(prefs);
        userRepository.save(user);
    }

    public Optional<User> getById(Long id) { return userRepository.findById(id); }
}



