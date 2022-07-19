package com.study;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    private String name;
    private String author_id;
    private String affiliations;

    public String getName() {
        return name;
    }
    public String getAuthor_id() {
        return author_id;
    }
    public String getAffiliations() {
        return affiliations;
    }

    public void DoShow() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Now enter the author's ID:");
        String paramForUrl = new BufferedReader(new InputStreamReader(System.in)).readLine();

        String queryWithParam = String.format("https://serpapi.com/search.json?engine=google_scholar_author&author_id=%s&api_key=eb97e736172f345de0427f6c3f3865d237ed6d099788cd9d502343bc7f88b9c4", paramForUrl);

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

                GoogleScholarAuthorResponse googleScholarResponse = objectMapper.readValue(json, GoogleScholarAuthorResponse.class);
                Author result = googleScholarResponse.getAuthor();
                System.out.println(result.getName());
                System.out.println(result.getAffiliations());
                CitedBy citedBy = googleScholarResponse.getCited_by();
                List<Table> tableList = citedBy.getTable();
                int i = 0;
                for (Table table : tableList) {
                    if (i == 0) {
                        Citations citations = table.getCitations();
                        System.out.println(citations.getAll());
                        i++;
                        continue;
                    }
                    if (i == 1) {
                        HIndex hIndex = table.getH_index();
                        System.out.println(hIndex.getAll());
                        break;
                    }
                }
                List<Articles> articlesList = googleScholarResponse.getArticles();
                for (Articles articles : articlesList) {
                    System.out.println(articles.getTitle());
                    System.out.println(articles.getYear());
                    ArticleCitedBy articleCitedBy = articles.getCited_by();
                    System.out.println(articleCitedBy.getValue());
                    System.out.println(articles.getLink());
                    System.out.println("----------");
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