package com.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("What are you looking for? Enter either U (university) or P (profile).\nIf you want to get more information about the author, enter A (author).");
        String input = sc.nextLine();

        switch (input) {
            case "U":
                int pagination = 0;

                System.out.println("Now enter your request:");

                String paramInCyrillic = new BufferedReader(new InputStreamReader(System.in)).readLine();
                String paramForUrl = URLEncoder.encode(paramInCyrillic, StandardCharsets.UTF_8.toString());

                UniversitySearch universitySearch = new UniversitySearch();
                universitySearch.setParamInCyrillic(paramInCyrillic);
                universitySearch.setParamForUrl(paramForUrl);
                universitySearch.setPagination(pagination);

                universitySearch.DoUSearch();

                System.out.println("Would you like to see 10 more results? Enter either Y (yes) or N (no).");
                input = sc.nextLine();
                while (Objects.equals(input, "Y")) {
                    pagination += 1;
                    universitySearch.setPagination(pagination);
                    universitySearch.DoUSearch();
                    System.out.println("Would you like to see 10 more results? Enter either Y (yes) or N (no).");
                    input = sc.nextLine();
                }
                break;

            case "P":
                System.out.println("Now enter your request:");

                paramInCyrillic = new BufferedReader(new InputStreamReader(System.in)).readLine();
                paramForUrl = URLEncoder.encode(paramInCyrillic, StandardCharsets.UTF_8.toString());

                ProfileSearch profileSearch = new ProfileSearch();
                profileSearch.setParamInCyrillic(paramInCyrillic);
                profileSearch.setParamForUrl(paramForUrl);

                profileSearch.DoPSearch();

                System.out.println("Would you like to see 10 more profiles? Enter either Y (yes) or N (no).");
                input = sc.nextLine();
                while (Objects.equals(input, "Y")) {
                    profileSearch.DoPSearch();
                    System.out.println("Would you like to see 10 more results? Enter either Y (yes) or N (no).");
                    input = sc.nextLine();
                }
                break;

            case "A":
                Author author = new Author();
                author.DoShow();
                break;
        }
    }
}
