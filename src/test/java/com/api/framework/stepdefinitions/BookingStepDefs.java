package com.api.framework.stepdefinitions;

import com.api.framework.api.BookingApi;
import com.api.framework.models.BookingDates;
import com.api.framework.models.BookingRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

public class BookingStepDefs {

    private BookingRequest booking;
    private Response response;

    @Given("I have a valid booking payload")
    public void i_have_a_valid_booking_payload() {
        booking = new BookingRequest.Builder()
                .setFirstName("Alice")
                .setLastName("Smith")
                .setTotalPrice(200)
                .setDepositPaid(false)
                .setBookingDates(new BookingDates("2025-06-10", "2025-06-20"))
                .setAdditionalNeeds("Dinner")
                .build();
    }

    @When("I send a POST request to create booking")
    public void i_send_a_post_request_to_create_booking() {
        response = BookingApi.createBooking(booking);
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Then("I should receive a response with status code 200")
    public void i_should_receive_a_response_with_status_code_200() {
        Assert.assertEquals(200, response.getStatusCode());
    }
}
