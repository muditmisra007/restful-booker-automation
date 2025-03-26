package com.api.framework.hooks;

import com.api.framework.api.AuthApi;
import com.api.framework.api.BookingApi;
import com.api.framework.stepdefinitions.BookingStepDefs;
import io.cucumber.java.After;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class Hooks {

    private String token;
    private final AuthApi authApi;
    private final BookingApi bookingApi;
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    // Constructor to initialize API clients
    public Hooks() {
        this.authApi = new AuthApi();
        this.bookingApi = new BookingApi();
    }

    @After("@RunHooks")
    public void deleteBooking() {
        if (BookingStepDefs.bookingId != 0) {
            token = authApi.getAuthToken("admin", "password123"); // Get authentication token

            Response response = bookingApi.deleteBooking(BookingStepDefs.bookingId, token);
            Assert.assertEquals("Booking deletion failed", 201, response.getStatusCode());

            logger.info("Booking deleted successfully: {}", BookingStepDefs.bookingId);
        }
    }
}
