package com.study;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HIndex {
    private int all;

    public int getAll() {
        return all;
    }
}

