package com.jacky.marketservice.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.jacky.marketservice.model.EOrderType;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

public class StatusEnumDeserializer extends JsonDeserializer<EOrderType> {

    @Override
    public EOrderType deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        String value = p.getText().toUpperCase();
        return EOrderType.valueOf(value);
    }
}
