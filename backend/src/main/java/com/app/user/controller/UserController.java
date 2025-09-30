package com.app.user.controller;

import com.app.user.dto.LoginDTO;
import com.app.user.dto.UserDTO;
import com.app.user.model.User;
import com.app.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

	// User Register
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO dto) {
        User created = userService.register(dto);
        return ResponseEntity.created(URI.create("/api/users/" + created.getId())).body(Map.of(
                "id", created.getId(),
                "name", created.getName(),
                "email", created.getEmail(),
                "username", created.getUsername()
        ));
    }
	
	// User Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dto) {
        return userService.login(dto)
                .<ResponseEntity<?>>map(u -> ResponseEntity.ok(Map.of(
                        "id", u.getId(),
                        "name", u.getName(),
                          "email", u.getEmail(),
                        "username", u.getUsername()
                )))
                .orElseGet(() -> ResponseEntity.status(401).body(Map.of("error", "Invalid credentials")));
    }

	// User Get Profile
    @GetMapping("/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Long id) {
        return userService.getById(id)
                .<ResponseEntity<?>>map(u -> ResponseEntity.ok(Map.of(
                        "id", u.getId(),
                        "name", u.getName(),
                        "email", u.getEmail(),
                        "username", u.getUsername(),
                        "preferences", u.getPreferences()
                )))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/preferences")
    public ResponseEntity<?> updatePreferences(@PathVariable Long id, @RequestBody Map<String, String> body) {
        userService.updatePreferences(id, body.getOrDefault("preferences", ""));
        return ResponseEntity.noContent().build();
    }
}



