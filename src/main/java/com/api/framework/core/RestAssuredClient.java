package com.api.framework.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class RestAssuredClient {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private static final Map<String, String> DEFAULT_HEADERS = new HashMap<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Static block to initialize default headers
    static {
        DEFAULT_HEADERS.put("Content-Type", "application/json");
    }

    // 🔹 Private method to merge default headers with additional headers
    private static Map<String, String> mergeHeaders(Map<String, String> additionalHeaders) {
        Map<String, String> headers = new HashMap<>(DEFAULT_HEADERS);
        if (additionalHeaders != null) {
            headers.putAll(additionalHeaders);
        }
        return headers;
    }

    public static Response get(String endpoint, Map<String, String> headers) {
        RequestSpecification request = RestAssured.given().headers(mergeHeaders(headers));
        return request.get(BASE_URL + endpoint);
    }

    public static Response get(String endpoint) {
        RequestSpecification request = RestAssured.given().headers(DEFAULT_HEADERS);
        return request.get(BASE_URL + endpoint);
    }

    public static Response post(String endpoint, Object body, Map<String, String> additionalHeaders) {
        RequestSpecification request = RestAssured.given();
        request.headers(mergeHeaders(additionalHeaders)).body(body);
        return request.post(BASE_URL + endpoint);
    }

    public static Response post(String endpoint, Object body) {
        RequestSpecification request = RestAssured.given().headers(DEFAULT_HEADERS).body(body);
        return request.post(BASE_URL + endpoint);
    }

    public static Response put(String endpoint, Object body, Map<String, String> headers) {
        RequestSpecification request = RestAssured.given().headers(mergeHeaders(headers)).body(body);
        return request.put(BASE_URL + endpoint);
    }

    public static Response delete(String endpoint, Map<String, String> headers) {
        RequestSpecification request = RestAssured.given().headers(mergeHeaders(headers)).log().all();
        return request.delete(BASE_URL + endpoint);
    }
}
