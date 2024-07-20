package com.jacky.data_collector_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootTest
class DataCollectorControllerApplicationTests {

	@Test
	void contextLoads() {
		String target = "items";
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.warframestat.us/" + target +"/"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		File jsonFile = new File(target + ".json");

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// Write the object as JSON to the file
			objectMapper.writeValue(jsonFile, objectMapper.readTree(response.body()));
			System.out.println("JSON written to file successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
