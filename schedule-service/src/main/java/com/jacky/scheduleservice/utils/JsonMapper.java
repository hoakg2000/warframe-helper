package com.jacky.scheduleservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;

import java.util.List;

public class JsonMapper<T> {
    private static final Logger log = LoggerFactory.getLogger(JsonMapper.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T convertStringToClass(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            log.error("Can't map object: {}", e.getMessage(), e);
            return null;
//            throw new RuntimeException("Failed to map JSON response to object", e);
        }
    }

    public<T> List<T> convertStringToList(String response, Class<T> clazz) {
        try {
            return objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            log.error("Can't map object: {}", e.getMessage(), e);
            return null;
//            throw new RuntimeException("Failed to map JSON response to list of " + clazz.getSimpleName(), e);
        }
    }
}
