package com.project.quotes.model;


import com.project.quotes.model.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Document(collection = "confirmationTokens")
public class ConfirmationToken {
    @Id
    private String id;
    @Field
    private String token;
    @Field
    private LocalDateTime createdAt;
    @Field
    private LocalDateTime expiresAt;
    @Field
    private LocalDateTime confirmedAt = null;
    @DocumentReference
    private User user;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
