package com.study;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleScholarUniversityResponse {

    private List<OrganicResult> organic_results;

    public List<OrganicResult> getOrganic_results() {
        return organic_results;
    }
}
