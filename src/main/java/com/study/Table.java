package com.study;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Table {
    private Citations citations;
    private HIndex h_index;

    public Citations getCitations() {
        return citations;
    }
    public HIndex getH_index() {
        return h_index;
    }
}
