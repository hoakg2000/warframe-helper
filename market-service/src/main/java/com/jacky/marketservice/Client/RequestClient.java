package com.jacky.marketservice.Client;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@NoArgsConstructor
public class RequestClient {

    public String get(String api){
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(api))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpRequest.Builder t = HttpRequest.newBuilder();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response.body();
    }
}
