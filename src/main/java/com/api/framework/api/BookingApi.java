package com.api.framework.api;

import com.api.framework.models.BookingRequest;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class BookingApi extends AbstractApi {

    private static final String BASE_URL = "/booking";

    public Response createBooking(BookingRequest booking) {
        return post(BASE_URL, booking);
    }

    public Response getBooking(int bookingId) {
        return get(BASE_URL + "/" + bookingId);
    }

    public Response getBookingIds() {
        return get(BASE_URL);
    }

    public Response updateBooking(int bookingId, BookingRequest booking, String authToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "token=" + authToken);
        headers.put("Accept", "application/json");

        return put(BASE_URL + "/" + bookingId, booking, headers);
    }

    public Response deleteBooking(int bookingId, String authToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "token=" + authToken);
        return delete(BASE_URL + "/" + bookingId, headers);
    }
}
