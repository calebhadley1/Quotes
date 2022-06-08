package com.project.quotes.controller;

import com.project.quotes.request.LoginRequest;
import com.project.quotes.request.RegistrationRequest;
import com.project.quotes.service.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @CrossOrigin
    @PostMapping()
    public String login(@RequestBody LoginRequest request) {
        System.out.println(request);
        return loginService.login(request);
    }

}
