package com.study;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;

public class UniversitySearch {
    private String university_search;
    private String paramInCyrillic;
    private String paramForUrl;
    private int pagination;

    public void setParamInCyrillic(String paramInCyrillic) {
        this.paramInCyrillic = paramInCyrillic;
    }
    public void setParamForUrl(String paramForUrl) {
        this.paramForUrl = paramForUrl;
    }
    public void setPagination(int pagination) {
        this.pagination = pagination;
    }

    public String getUniversity_search() {
        return university_search;
    }

    public void DoUSearch() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String queryWithParam = String.format("https://serpapi.com/search.json?engine=google_scholar&q=%s&start=%s&api_key=eb97e736172f345de0427f6c3f3865d237ed6d099788cd9d502343bc7f88b9c4", paramForUrl, pagination);

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(queryWithParam).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);

            connection.connect();

            StringBuilder sb = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }

                String json = sb.toString();

                GoogleScholarUniversityResponse googleScholarResponse = objectMapper.readValue(json, GoogleScholarUniversityResponse.class);
                for (OrganicResult result : googleScholarResponse.getOrganic_results()) {
                    System.out.println(result.getTitle());
                    System.out.println(result.getLink());
                    PublicationInfo publicationInfo = result.getPublication_info();
                    System.out.println(publicationInfo.getSummary());
                    List<Author> authorList = publicationInfo.getAuthors();
                    if (authorList != null) {
                        for (Author author : publicationInfo.getAuthors()) {
                            if (author == null) {
                                System.out.println("No information available");
                            } else {
                                if (author.getName() != null) {
                                    System.out.println(author.getName());
                                    System.out.println(author.getAuthor_id());
                                } else {
                                    System.out.println("No information available");
                                }
                            }
                        }
                    } else {
                        System.out.println("No information available");
                    }
                    System.out.println("--------------------");
                }

            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }

        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}


