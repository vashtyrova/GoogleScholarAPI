package com.study;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleScholarProfileResponse {
    private List<Profiles> profiles;
    private ProfileSearchPagination pagination;

    public List<Profiles> getProfiles() {
        return profiles;
    }
    public ProfileSearchPagination getPagination() {
        return pagination;
    }
}
