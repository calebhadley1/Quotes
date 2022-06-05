package com.project.quotes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "quotes")
public class Quote {
    @Id
    private String id;
    @Field(name="name")
    @Indexed(unique = true)
    private String quoteName;
    @Field(name="category")
    private QuoteCategory quoteCategory;
    @Field(name="date")
    private Date quoteDate;

    public Quote(String id, String quoteName, QuoteCategory quoteCategory, Date quoteDate) {
        this.id = id;
        this.quoteName = quoteName;
        this.quoteCategory = quoteCategory;
        this.quoteDate = quoteDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuoteName() {
        return quoteName;
    }

    public void setQuoteName(String quoteName) {
        this.quoteName = quoteName;
    }

    public QuoteCategory getQuoteCategory() {
        return quoteCategory;
    }

    public void setQuoteCategory(QuoteCategory quoteCategory) {
        this.quoteCategory = quoteCategory;
    }

    public Date getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }
}
