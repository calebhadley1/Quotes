package com.project.quotes.service;

import com.project.quotes.request.LoginRequest;
import com.project.quotes.response.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserService userService;

    public LoginService(UserService userService) {
        this.userService = userService;
    }

    public AuthResponse login(LoginRequest request) {
        return userService.login(request);
    }
}
