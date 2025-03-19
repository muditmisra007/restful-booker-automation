package com.api.framework.api;

import com.api.framework.core.RestAssuredClient;
import com.api.framework.models.BookingRequest;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class BookingApi {

    public static Response createBooking(BookingRequest booking) {
        return RestAssuredClient.post("/booking", booking);
    }

    public static Response getBooking(int bookingId) {
        return RestAssuredClient.get("/booking/" + bookingId);
    }

    public static Response updateBooking(int bookingId, BookingRequest booking, String authToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "token=" + authToken);
        headers.put("Accept", "application/json");

        return RestAssuredClient.put("/booking/" + bookingId, booking, headers);
    }

    public static Response deleteBooking(int bookingId, String authToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "token=" + authToken);
        return RestAssuredClient.delete("/booking/" + bookingId, headers);
    }
}
