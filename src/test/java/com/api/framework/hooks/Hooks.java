package com.api.framework.hooks;

import com.api.framework.api.AuthApi;
import com.api.framework.api.BookingApi;
import com.api.framework.stepdefinitions.BookingStepDefs;
import io.cucumber.java.After;
import io.restassured.response.Response;
import org.junit.Assert;

public class Hooks {

    private String token;

    @After("@RunHooks")
    public void deleteBooking() {
        if (BookingStepDefs.bookingId != 0) {
            token = AuthApi.getAuthToken("admin", "password123"); // Get authentication token

            Response response = BookingApi.deleteBooking(BookingStepDefs.bookingId, token);
            Assert.assertEquals("Booking deletion failed", 201, response.getStatusCode());

            System.out.println("Booking deleted successfully: " + BookingStepDefs.bookingId);
        }
    }
}
