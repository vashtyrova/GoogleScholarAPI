package com.study;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganicResult {
    private String title;
    private String link;
    private PublicationInfo publication_info;

    public PublicationInfo getPublication_info() {
        return publication_info;
    }
    public String getLink() {
        return link;
    }
    public String getTitle() {
        return title;
    }
}
