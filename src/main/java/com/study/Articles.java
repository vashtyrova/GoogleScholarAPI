package com.study;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Articles {
    private String title;
    private String link;
    private int year;
    private ArticleCitedBy cited_by;

    public String getTitle() {
        return title;
    }
    public String getLink() {
        return link;
    }
    public int getYear() {
        return year;
    }
    public ArticleCitedBy getCited_by() {
        return cited_by;
    }
}
