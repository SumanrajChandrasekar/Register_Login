package com.example.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.login.entity.User;
import com.example.login.payload.LoginResponse;
import com.example.login.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> signup(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        boolean isValid = userService.validateUser(user.getUsername(), user.getPassword(), user.getRole());

        if (isValid) {
            return ResponseEntity.ok(
                new LoginResponse("Login successful", user.getUsername(), user.getRole())
            );
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Invalid credentials", null, null));
        }
    }
}