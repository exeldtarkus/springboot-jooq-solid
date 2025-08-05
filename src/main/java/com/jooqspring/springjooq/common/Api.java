package com.jooqspring.springjooq.common;

import org.jooq.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.jooqspring.springjooq.utils.JsonUtils;

@Component
public class Api {
    private final RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(Api.class);

    public Api(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @SuppressWarnings("unchecked")
	private <T, R> ResponseEntity<R> index(
            String apiEndpoint,
            HttpMethod httpMethod,
            HttpHeaders httpHeaders,
            T requestBody,
            Class<R> classToConvertBodyTo
    ) {
        String logTemplate = String.format("[%s : %s] - ", httpMethod, apiEndpoint);

        HttpEntity<T> requestEntity = new HttpEntity<>(requestBody, httpHeaders);

        String reqEntityMask = requestBody instanceof String
                ? requestBody.toString()
                    : requestBody instanceof JsonNode
                        ? JsonUtils.Masking((JsonNode) requestBody)
                            : requestBody instanceof JSON
                                ? JsonUtils.Masking((JSON) requestBody)
                                    : JsonUtils.Masking(requestBody);

        logger.info(logTemplate + "[Header] - " + httpHeaders + " - [Body] - " + reqEntityMask);

        try {
            return restTemplate.exchange(
                    apiEndpoint,
                    httpMethod,
                    requestEntity,
                    classToConvertBodyTo
            );
        } catch (HttpClientErrorException e) {
            logger.warn(logTemplate + "[Warning] - [Message] - " + e.getMessage());
            logger.error(logTemplate + "[Error] - " + e);
            ResponseEntity<String> errorResponseEntity = ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
            return (ResponseEntity<R>) errorResponseEntity;
        }
    }

    public <T, R> ResponseEntity<String> get(
            String url,
            HttpHeaders httpHeaders
    ) {
        return this.index(url, HttpMethod.GET, httpHeaders, null, String.class);
    }

    public <T, R> ResponseEntity<String> post(
            String url,
            HttpHeaders httpHeaders,
            T requestBody
    ) {
        return this.index(url, HttpMethod.POST, httpHeaders, requestBody, String.class);
    }

    public <T, R> ResponseEntity<String> put(
            String url,
            HttpHeaders httpHeaders,
            T requestBody
    ) {
        return this.index(url, HttpMethod.PUT, httpHeaders, requestBody, String.class);
    }

    public <T, R> ResponseEntity<String> patch(
            String url,
            HttpHeaders httpHeaders,
            T requestBody
    ) {
        return this.index(url, HttpMethod.PATCH, httpHeaders, requestBody, String.class);
    }

    public <T, R> ResponseEntity<String> delete(
            String url,
            HttpHeaders httpHeaders,
            T requestBody
    ) {
        return this.index(url, HttpMethod.DELETE, httpHeaders, requestBody, String.class);
    }

    public <T, R> ResponseEntity<String> options(
            String url,
            HttpHeaders httpHeaders,
            T requestBody
    ) {
        return this.index(url, HttpMethod.OPTIONS, httpHeaders, requestBody, String.class);
    }

    public <T, R> ResponseEntity<String> trace(
            String url,
            HttpHeaders httpHeaders,
            T requestBody
    ) {
        return this.index(url, HttpMethod.TRACE, httpHeaders, requestBody, String.class);
    }

    public <T, R> ResponseEntity<String> head(
            String url,
            HttpHeaders httpHeaders,
            T requestBody
    ) {
        return this.index(url, HttpMethod.HEAD, httpHeaders, requestBody, String.class);
    }
}
