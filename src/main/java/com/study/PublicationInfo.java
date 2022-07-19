package com.study;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicationInfo {
    private String summary;
    private List<Author> authors;

    public List<Author> getAuthors() {
        return authors;
    }
    public String getSummary() {
        return summary;
    }
}
