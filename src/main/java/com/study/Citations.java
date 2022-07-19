package com.study;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Citations {
    private int all;

    public int getAll() {
        return all;
    }
}
