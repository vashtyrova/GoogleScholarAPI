package com.study;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleCitedBy {
    private int value;

    public int getValue() {
        return value;
    }
}
