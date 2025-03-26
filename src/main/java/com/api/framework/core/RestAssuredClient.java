package com.api.framework.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class RestAssuredClient implements IApiClient {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private static final Map<String, String> DEFAULT_HEADERS = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(RestAssuredClient.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        DEFAULT_HEADERS.put("Content-Type", "application/json");
    }

    // 🔹 Merge default headers with additional headers
    private static Map<String, String> mergeHeaders(Map<String, String> additionalHeaders) {
        Map<String, String> headers = new HashMap<>(DEFAULT_HEADERS);
        if (additionalHeaders != null) {
            headers.putAll(additionalHeaders);
        }
        return headers;
    }

    @Override
    public Response get(String endpoint, Map<String, String> headers) {
        String url = BASE_URL + endpoint;
        logger.info("Sending GET request to: {}", url);
        logger.debug("Headers: {}", mergeHeaders(headers));

        Response response = RestAssured.given().headers(mergeHeaders(headers)).get(url);
        logResponse(response);
        return response;
    }

    @Override
    public Response get(String endpoint) {
        String url = BASE_URL + endpoint;
        logger.info("Sending GET request to: {}", url);
        logger.debug("Headers: {}", DEFAULT_HEADERS);

        Response response = RestAssured.given().headers(DEFAULT_HEADERS).get(url);
        logResponse(response);
        return response;
    }

    @Override
    public Response post(String endpoint, Object body, Map<String, String> headers) {
        String url = BASE_URL + endpoint;
        logger.info("Sending POST request to: {}", url);
        logger.debug("Headers: {}", mergeHeaders(headers));
        try {
            String jsonBody = objectMapper.writeValueAsString(body);
            logger.debug("Request Body: {}", jsonBody);
        } catch (JsonProcessingException e) {
            logger.error("Error converting request body to JSON", e);
        }

        Response response = RestAssured.given().headers(mergeHeaders(headers)).body(body).post(url);
        logResponse(response);
        return response;
    }

    @Override
    public Response post(String endpoint, Object body) {
        return post(endpoint, body, DEFAULT_HEADERS);
    }

    @Override
    public Response put(String endpoint, Object body, Map<String, String> headers) {
        String url = BASE_URL + endpoint;
        logger.info("Sending PUT request to: {}", url);
        logger.debug("Headers: {}", mergeHeaders(headers));
        logger.debug("Request Body: {}", body);

        Response response = RestAssured.given().headers(mergeHeaders(headers)).body(body).put(url);
        logResponse(response);
        return response;
    }

    @Override
    public Response delete(String endpoint, Map<String, String> headers) {
        String url = BASE_URL + endpoint;
        logger.info("Sending DELETE request to: {}", url);
        logger.debug("Headers: {}", mergeHeaders(headers));

        Response response = RestAssured.given().headers(mergeHeaders(headers)).log().all().delete(url);
        logResponse(response);
        return response;
    }

    // 🔹 Log Response Details
    private void logResponse(Response response) {
        if (response != null) {
            logger.info("Response Status: {}", response.getStatusCode());
            logger.debug("Response Body: {}", response.getBody().asPrettyString());
        } else {
            logger.warn("Received null response");
        }
    }
}
