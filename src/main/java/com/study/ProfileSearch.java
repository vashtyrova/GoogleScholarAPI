package com.study;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProfileSearch {
    private String profile_search;
    private String paramInCyrillic;
    private String paramForUrl;
    private String after_author = null;

    public void setParamInCyrillic(String paramInCyrillic) {
        this.paramInCyrillic = paramInCyrillic;
    }
    public void setParamForUrl(String paramForUrl) {
        this.paramForUrl = paramForUrl;
    }

    public String getProfile_search() {
        return profile_search;
    }

    public void DoPSearch() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String queryWithParam = String.format("https://serpapi.com/search.json?engine=google_scholar_profiles&mauthors=%s&after_author=%s&api_key=eb97e736172f345de0427f6c3f3865d237ed6d099788cd9d502343bc7f88b9c4", paramForUrl, after_author);

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

                GoogleScholarProfileResponse googleScholarResponse = objectMapper.readValue(json, GoogleScholarProfileResponse.class);
                for (Profiles result : googleScholarResponse.getProfiles()) {
                    System.out.println(result.getName());
                    System.out.println(result.getLink());
                    System.out.println(result.getAuthor_id());
                    System.out.println(result.getAffiliations());
                    System.out.println(result.getCited_by());
                    System.out.println("--------------------");
                }
                ProfileSearchPagination profileSearchPagination = googleScholarResponse.getPagination();
                after_author = profileSearchPagination.getNext_page_token();

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
