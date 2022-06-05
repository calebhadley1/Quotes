package com.project.quotes.repository;

import com.project.quotes.model.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface QuoteRepository extends MongoRepository<Quote, String> {
    @Query("{'name':  ?0}")
    Optional<Quote> findByName(String name);
}
