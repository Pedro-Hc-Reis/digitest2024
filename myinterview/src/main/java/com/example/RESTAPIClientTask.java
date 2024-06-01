package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * This task expects you to create an implementation of a Rest API client.
 * Your code should call the API endpoint related below.
 * After receiving the JSON Response, print out how many records exists for each gender
 * API endpoint => https://3ospphrepc.execute-api.us-west-2.amazonaws.com/prod/RDSLambda
 * <p>
 * >>> Bonus <<<
 * Generate a CSV file containing many records exists for each gender and save this file to AWS S3 Bucket
 * The filename need to contains your entire name, separated by uderscore. Example: john_lennon.csv
 * AWS S3 bucket name => interview-digiage
 * The credentials you can find in Coodesh platform or ask via e-mail for recrutamento@digiage.com.br
 */

public class RESTAPIClientTask {

    private static final String API_URL = "https://3ospphrepc.execute-api.us-west-2.amazonaws.com/prod/RDSLambda";

    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(API_URL);
            HttpResponse response = httpClient.execute(request);

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder jsonResponse = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse.toString());

            int maleCount = 0;
            int femaleCount = 0;
            int otherCount = 0;

            for (JsonNode record : rootNode) {
                String gender = record.path("gender").asText();
                if ("M".equalsIgnoreCase(gender)) {
                    maleCount++;
                } else if ("F".equalsIgnoreCase(gender)) {
                    femaleCount++;
                } else {
                    otherCount++;
                }
            }

            System.out.println("Male: " + maleCount);
            System.out.println("Female: " + femaleCount);
            System.out.println("Other: " + otherCount);

        } catch (Exception e) {
            System.err.println("Error occurred while processing the API response: " + e.getMessage());
        }
    }
}