package com.project.quotes.service;

import com.project.quotes.request.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserService userService;

    public LoginService(UserService userService) {
        this.userService = userService;
    }

    public String login(LoginRequest request) {
        return userService.login(request);
    }
}
