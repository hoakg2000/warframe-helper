package com.jacky.scheduleservice.Client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacky.scheduleservice.model.TrackingItem;
import com.jacky.scheduleservice.utils.JsonMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class RequestClient{

    public String get(String api){
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(api))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .header("Platform", "pc")
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        if (response == null){
            return "";
        }
        return response.body();
    }

}
