package com.api.framework.api;

import com.api.framework.core.RestAssuredClient;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class AuthApi {
    private final RestAssuredClient restAssuredClient;

    // Constructor to initialize RestAssuredClient
    public AuthApi() {
        this.restAssuredClient = new RestAssuredClient();
    }

    public String getAuthToken(String username, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);

        Response response = restAssuredClient.post("/auth", body);
        return response.jsonPath().getString("token");
    }
}
