package com.project.quotes.controller;

import com.project.quotes.request.RegistrationRequest;
import com.project.quotes.response.AuthResponse;
import com.project.quotes.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @CrossOrigin
    @PostMapping()
    public ResponseEntity<AuthResponse> register(@RequestBody RegistrationRequest request) {
        System.out.println(request);
        return ResponseEntity.ok(registrationService.register(request));
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token")  String token) {
        return registrationService.confirmToken(token);
    }
}
