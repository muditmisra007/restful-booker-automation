package com.api.framework.stepdefinitions;

import com.api.framework.api.BookingApi;
import com.api.framework.models.BookingDates;
import com.api.framework.models.BookingRequest;
import com.api.framework.models.BookingRequestBuilder;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.Map;

public class BookingStepDefs {
    private Response response;
    private final BookingApi bookingApi = new BookingApi();
    public static int bookingId;
    public static BookingRequest requestBody;
    private static final Logger logger = LogManager.getLogger(BookingStepDefs.class);

    @Given("I create a new booking with the following details:")
    public void createBooking(DataTable table) {
        Map<String, String> bookingData = table.asMap(String.class, String.class);

        logger.info("Building booking request from provided data...");
        requestBody = buildBookingRequest(bookingData);

        logger.info("Sending booking request...");
        response = bookingApi.createBooking(requestBody);

        // Validate response
        Assert.assertEquals("Booking creation failed, expected 200 but got " + response.getStatusCode(),
                200, response.getStatusCode());

        // Extract and validate booking ID
        bookingId = response.jsonPath().getInt("bookingid");
        Assert.assertNotNull("Booking ID should not be null", bookingId);
        logger.info("Booking successfully created with ID: {}", bookingId);
    }

    @Then("the booking should be created successfully")
    public void validateBookingCreation() {
        logger.info("Verifying booking exists for ID: {}", bookingId);
        response = bookingApi.getBooking(bookingId);

        logger.info("Received response with status: {}", response.getStatusCode());
        Assert.assertEquals("Booking not found, expected 200 but got " + response.getStatusCode(),
                200, response.getStatusCode());

        logger.info("Booking verification successful!");
    }

    // 🔹 Helper Method: Build Booking Request from DataTable
    private BookingRequest buildBookingRequest(Map<String, String> bookingData) {
        BookingDates bookingDates = new BookingDates(bookingData.get("checkin"), bookingData.get("checkout"));

        return new BookingRequestBuilder()
                .setFirstName(bookingData.get("firstname"))
                .setLastName(bookingData.get("lastname"))
                .setTotalPrice(Integer.parseInt(bookingData.get("totalprice")))
                .setDepositPaid(Boolean.parseBoolean(bookingData.get("depositpaid")))
                .setBookingDates(bookingDates)
                .setAdditionalNeeds(bookingData.get("additionalneeds"))
                .build();
    }
}
