package com.example.login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.entity.User;
import com.example.login.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }

  
    public boolean validateUser(String username, String password, String role) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsernameAndPasswordAndRole(username, password, role));
        return user.isPresent();
    }
}