package com.jooqspring.springjooq.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseJson {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts Java object to JSON string
     * @param object Java object to be serialized
     * @return JSON string representation of the object
     * @throws JsonProcessingException If there's an error during serialization
     */
    public static String Stringify(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    /**
     * Converts JSON string to Java object
     * @param json JSON string to be deserialized
     * @param valueType Class of the object to deserialize to
     * @param <T> Type of the Java object
     * @return Java object deserialized from the JSON string
     * @throws JsonMappingException If there's an error mapping JSON to Java object
     * @throws JsonProcessingException If there's an error during deserialization
     */
    public static <T> T Parse(String json, Class<T> valueType) throws JsonProcessingException {
        return objectMapper.readValue(json, valueType);
    }

    /**
     * Parses JSON string to JsonNode (tree structure)
     * @param json JSON string to be converted into JsonNode
     * @return JsonNode representing the JSON string
     * @throws JsonProcessingException If there's an error during parsing
     */
    public static JsonNode ReadTree(String json) throws JsonProcessingException {
        return objectMapper.readTree(json);
    }
}
