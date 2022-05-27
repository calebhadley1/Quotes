package com.project.quotes.service;

import com.project.quotes.model.ConfirmationToken;
import com.project.quotes.repository.ConfirmationTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public ConfirmationToken getToken(String token) {
        return confirmationTokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException(
                String.format("Cannot find Token by token %s", token)));
    }

    public void updateConfirmationToken(ConfirmationToken token) {
        ConfirmationToken savedConfirmationToken = confirmationTokenRepository.findById(token.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Token by ID %s", token.getId())));

        savedConfirmationToken.setToken(token.getToken());
        savedConfirmationToken.setConfirmedAt(token.getConfirmedAt());
        savedConfirmationToken.setCreatedAt(token.getCreatedAt());
        savedConfirmationToken.setExpiresAt(token.getExpiresAt());

        confirmationTokenRepository.save(savedConfirmationToken);
    }

    public void setConfirmedAt(String token) {
        ConfirmationToken savedConfirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Token by Token %s", token)));
        savedConfirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenRepository.save(savedConfirmationToken);
    }
}
