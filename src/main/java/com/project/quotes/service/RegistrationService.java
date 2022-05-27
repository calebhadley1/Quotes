package com.project.quotes.service;

import com.project.quotes.model.User;
import com.project.quotes.model.UserRole;
import com.project.quotes.request.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final EmailValidatorService emailValidator;
    private final UserService userService;

    public RegistrationService(EmailValidatorService emailValidator, UserService userService) {
        this.emailValidator = emailValidator;
        this.userService = userService;
    }

    public String register(RegistrationRequest request){
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(isValidEmail)
            return userService.signup(new User(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPassword(),
                    UserRole.USER
            ));
        throw new IllegalStateException("Email not valid");
    }

}
