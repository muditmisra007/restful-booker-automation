package com.api.framework.api;

import com.api.framework.core.IApiClient;
import com.api.framework.core.RestAssuredClient;
import io.restassured.response.Response;
import java.util.Map;

public abstract class AbstractApi {
    protected IApiClient apiClient;

    public AbstractApi() {
        this.apiClient = new RestAssuredClient(); // You can swap this with another implementation if needed
    }

    protected Response get(String endpoint, Map<String, String> headers) {
        return apiClient.get(endpoint, headers);
    }

    protected Response get(String endpoint) {
        return apiClient.get(endpoint);
    }

    protected Response post(String endpoint, Object body, Map<String, String> headers) {
        return apiClient.post(endpoint, body, headers);
    }

    protected Response post(String endpoint, Object body) {
        return apiClient.post(endpoint, body);
    }

    protected Response put(String endpoint, Object body, Map<String, String> headers) {
        return apiClient.put(endpoint, body, headers);
    }

    protected Response delete(String endpoint, Map<String, String> headers) {
        return apiClient.delete(endpoint, headers);
    }
}
