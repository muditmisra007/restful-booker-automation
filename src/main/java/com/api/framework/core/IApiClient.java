package com.api.framework.core;

import io.restassured.response.Response;

import java.util.Map;

public interface IApiClient {
    Response get(String endpoint);

    Response post(String endpoint, Object body, Map<String, String> additionalHeaders);

    Response put(String endpoint, Object body);

    Response delete(String endpoint);
}
