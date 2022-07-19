package com.study;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileSearchPagination {
    private String next_page_token;

    public String getNext_page_token() {
        return next_page_token;
    }
}
