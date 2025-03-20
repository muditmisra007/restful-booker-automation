package com.api.framework.stepdefinitions;

import com.api.framework.api.BookingApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetBookingIdSteps {
    private Response response;
    private List<Integer> bookingIds;
    private Map<String, Object> bookingDetails;

    @When("I send a request to get all bookings")
    public void i_send_a_request_to_get_all_bookings() {
        response = BookingApi.getBookingIds();

    }

    @Then("the response should return a list of booking IDs")
    public void the_response_should_return_a_list_of_booking_i_ds() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Integer>> bookings = objectMapper.readValue(response.getBody().asString(),
                new TypeReference<List<Map<String, Integer>>>() {
                });
        bookingIds = bookings.stream().map(booking -> booking.get("bookingid")).collect(Collectors.toList());
        Assert.assertTrue(response.getBody().asString().contains("bookingid"));
        Assert.assertTrue(!bookingIds.isEmpty());
    }

    @Then("the status code should be {int}")
    public void the_status_code_should_be(Integer expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.getStatusCode(), "Request Failed");
    }

    @When("I send a request with booking id")
    public void i_send_a_request_with_booking_id() {
        response = BookingApi.getBooking(BookingStepDefs.bookingId);
        bookingDetails = response.jsonPath().getMap("");
    }

    @Then("the response should return the booking details")
    public void the_response_should_return_the_booking_details() {
        Assert.assertNotNull(response.getBody().asString());
    }

    @Then("the returned booking details must be same as booking creation details")
    public void the_returned_booking_details_must_be_same_as_booking_creation_details() {
        Assert.assertEquals(BookingStepDefs.requestBody.getFirstname(), bookingDetails.get("firstname"), "Firstname mismatch!");
        Assert.assertEquals(BookingStepDefs.requestBody.getLastname(), bookingDetails.get("lastname"), "Lastname mismatch!");
        Assert.assertEquals(BookingStepDefs.requestBody.getTotalprice(), bookingDetails.get("totalprice"), "Total price mismatch!");
        Assert.assertEquals(BookingStepDefs.requestBody.isDepositpaid(), bookingDetails.get("depositpaid"), "Deposit paid mismatch!");
        Assert.assertEquals(BookingStepDefs.requestBody.getAdditionalneeds(), bookingDetails.get("additionalneeds"), "Additional needs mismatch!");

        // Validate booking dates
        Map<String, String> bookingDatesMap = (Map<String, String>) bookingDetails.get("bookingdates");
        Assert.assertEquals(BookingStepDefs.requestBody.getBookingdates().getCheckin(), bookingDatesMap.get("checkin"), "Check-in date mismatch!");
        Assert.assertEquals(BookingStepDefs.requestBody.getBookingdates().getCheckout(), bookingDatesMap.get("checkout"), "Check-out date mismatch!");

    }
}
