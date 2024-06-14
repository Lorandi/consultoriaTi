package com.consultoriaTi.gestao.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;

public class JsonUtils {

    private static final ObjectMapper objectMapper;

    private JsonUtils() {
        throw new UnsupportedOperationException("JsonUtils class cannot be instantiated");
    }

    static {
        objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static void logObject(Logger logger, String operation, Object object) {
        try {
            String json = objectMapper.writeValueAsString(object);
            logger.info("\n\n{}:  \n{}", operation, json);
        } catch (JsonProcessingException e) {
            logger.error("Error converting object to JSON", e);
        }
    }
}
