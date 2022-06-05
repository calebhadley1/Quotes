package com.project.quotes.service;

import com.project.quotes.model.ConfirmationToken;
import com.project.quotes.model.User;
import com.project.quotes.model.UserRole;
import com.project.quotes.request.RegistrationRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    private final ConfirmationTokenService confirmationTokenService;
    private final EmailValidatorService emailValidator;
    private final UserService userService;

    public RegistrationService(ConfirmationTokenService confirmationTokenService, EmailValidatorService emailValidator, UserService userService) {
        this.confirmationTokenService = confirmationTokenService;
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

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getUsername());
        return "confirmed";
    }
}
