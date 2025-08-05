package com.jooqspring.springjooq.utils;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jooqspring.springjooq.common.BaseJson;

public class JsonUtils {
    // List of sensitive fields that should be masked
    private static final List<String> SENSITIVE_FIELDS = Arrays.asList("password", "token", "apiKey");

    /**
     * Masks sensitive fields in the JSON Node.
     * @param jsonNode The JSON Node to mask.
     */
    private static void MaskSensitiveFields(JsonNode jsonNode) {
        for (String field : SENSITIVE_FIELDS) {
            if (jsonNode.has(field)) {
                // Masking the field value with asterisks
                String maskedValue = "*".repeat(jsonNode.get(field).asText().length());
                ((ObjectNode) jsonNode).put(field, maskedValue);
            }
        }
    }

    /**
     * Mask sensitive values (like password, token, apiKey) in JSON string.
     * @param input The object (could be request body, response body, or any JSON-like object) to be processed.
     * @param <T> The type of the input object.
     * @return JSON string with sensitive values masked.
     */
    public static <T> String Masking(T input) {
        try {
            if (input == null) {
                return "{}";
            }

            // Convert the input object to JSON string
            String jsonString = BaseJson.Stringify(input);

            // Parse the JSON string into a JsonNode (tree-like structure)
            JsonNode jsonNode = BaseJson.ReadTree(jsonString);

            // Mask sensitive fields
            MaskSensitiveFields(jsonNode);

            // Return the modified JSON string with sensitive values masked
            return BaseJson.Stringify(jsonNode);

        } catch (JsonProcessingException e) {
            // Log and handle error if something goes wrong during JSON processing
            e.printStackTrace();
            return "{}";
        }
    }
}
