package com.study;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleScholarAuthorResponse {
    private Author author;
    private CitedBy cited_by;
    private List<Articles> articles;

    public Author getAuthor() {
        return author;
    }
    public CitedBy getCited_by() {
        return cited_by;
    }
    public List<Articles> getArticles() {
        return articles;
    }
}
