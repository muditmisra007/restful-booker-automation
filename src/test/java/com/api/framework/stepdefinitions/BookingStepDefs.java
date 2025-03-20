package com.api.framework.stepdefinitions;

import com.api.framework.api.BookingApi;
import com.api.framework.models.BookingDates;
import com.api.framework.models.BookingRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

public class BookingStepDefs {
    private Response response;
    public static int bookingId;  // Static variable to share booking ID
    public static BookingRequest requestBody;

    @Given("I create a new booking with the following details:")
    public void createBooking(DataTable table) {
        Map<String, String> bookingData = table.asMap(String.class, String.class);

        // Build request data
        BookingDates bookingDates = new BookingDates(bookingData.get("checkin"), bookingData.get("checkout"));

        requestBody = new BookingRequest.Builder()
                .setFirstName(bookingData.get("firstname"))
                .setLastName(bookingData.get("lastname"))
                .setTotalPrice(Integer.parseInt(bookingData.get("totalprice")))
                .setDepositPaid(Boolean.parseBoolean(bookingData.get("depositpaid")))
                .setBookingDates(bookingDates)
                .setAdditionalNeeds(bookingData.get("additionalneeds"))
                .build();

        // Send API request
        response = BookingApi.createBooking(requestBody);
        response.prettyPrint();
        Assert.assertEquals("Booking creation failed", 200, response.getStatusCode());

        // Extract booking ID from response
        bookingId = response.jsonPath().getInt("bookingid");
        Assert.assertNotEquals("Booking ID should not be null", 0, bookingId);
    }

    @Then("the booking should be created successfully")
    public void validateBookingCreation() {
        response = BookingApi.getBooking(bookingId);
        Assert.assertEquals("Booking not found", 200, response.getStatusCode());
    }
}
