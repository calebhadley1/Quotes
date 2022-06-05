package com.project.quotes.service;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidatorService implements Predicate<String> {

    @Override
    public boolean test(String email) {
        //TODO: regex for testing email
        return true;
    }
}
