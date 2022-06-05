package com.project.quotes.repository;

import com.project.quotes.model.ConfirmationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, String> {
    @Query("{'token':  ?0}")
    Optional<ConfirmationToken> findByToken(String token);
}
