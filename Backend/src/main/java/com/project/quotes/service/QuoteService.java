package com.project.quotes.service;

import com.project.quotes.model.Quote;
import com.project.quotes.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository){
        this.quoteRepository = quoteRepository;
    }

    public void addQuote(Quote quote) {
        quoteRepository.insert(quote);
    }

    public void updateQuote(Quote quote) {
        Quote savedQuote = quoteRepository.findById(quote.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Quote by ID %s", quote.getId())));

        savedQuote.setQuoteName(quote.getQuoteName());
        savedQuote.setQuoteCategory(quote.getQuoteCategory());
        savedQuote.setQuoteDate(quote.getQuoteDate());

        quoteRepository.save(savedQuote);

    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    public Quote getQuoteByName(String name) {
        return quoteRepository.findByName(name).orElseThrow(() -> new RuntimeException(
                String.format("Cannot find Quote by Name %s", name)));
    }

    public void deleteQuote(String id) {
        quoteRepository.deleteById(id);
    }
}
