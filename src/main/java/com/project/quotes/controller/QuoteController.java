package com.project.quotes.controller;

import com.project.quotes.model.Quote;
import com.project.quotes.service.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quote")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public ResponseEntity addQuote(@RequestBody Quote quote) {
        quoteService.addQuote(quote);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity updateQuote(@RequestBody Quote quote) {
        quoteService.updateQuote(quote);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Quote>> getAllQuotes() {
        return ResponseEntity.ok(quoteService.getAllQuotes());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Quote> getQuoteByName(@PathVariable String name) {
        return ResponseEntity.ok(quoteService.getQuoteByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuote(@PathVariable String id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
