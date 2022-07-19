package com.study;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CitedBy {
    private List<Table> table;

    public List<Table> getTable() {
        return table;
    }
}
