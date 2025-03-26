package com.api.framework.stepdefinitions;

import com.api.framework.api.BookingApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetBookingIdSteps {
    private Response response;
    private final BookingApi bookingApi = new BookingApi();
    private List<Integer> bookingIds;
    private Map<String, Object> bookingDetails;
    private static final Logger logger = LogManager.getLogger(GetBookingIdSteps.class);

    @When("I send a request to get all bookings")
    public void i_send_a_request_to_get_all_bookings() {
        logger.info("Sending request to fetch all booking IDs...");
        response = bookingApi.getBookingIds();
    }

    @Then("the response should return a list of booking IDs")
    public void the_response_should_return_a_list_of_booking_ids() {
        logger.info("Parsing booking ID response...");
        bookingIds = extractBookingIds(response);

        logger.debug("Extracted booking IDs: {}", bookingIds);
        Assert.assertFalse(bookingIds.isEmpty(), "Booking ID list is empty!");
    }

    @Then("the status code should be {int}")
    public void the_status_code_should_be(Integer expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        logger.info("Verifying status code. Expected: {}, Actual: {}", expectedStatusCode, actualStatusCode);
        Assert.assertEquals(actualStatusCode, expectedStatusCode.intValue(), "Unexpected status code!");
    }

    @When("I send a request with booking id")
    public void i_send_a_request_with_booking_id() {
        logger.info("Fetching details for booking ID: {}", BookingStepDefs.bookingId);
        response = bookingApi.getBooking(BookingStepDefs.bookingId);
        bookingDetails = response.jsonPath().getMap("");

        logger.debug("Fetched booking details: {}", bookingDetails);
    }

    @Then("the response should return the booking details")
    public void the_response_should_return_the_booking_details() {
        String responseBody = response.getBody().asString();
        logger.info("Verifying response body is not empty...");
        Assert.assertNotNull(responseBody, "Booking details response is null or empty!");
    }

    @Then("the returned booking details must be same as booking creation details")
    public void the_returned_booking_details_must_be_same_as_booking_creation_details() {
        logger.info("Validating booking details against expected values...");

        // Validate basic booking details
        Assert.assertEquals(BookingStepDefs.requestBody.getFirstname(), bookingDetails.get("firstname"), "Firstname mismatch!");
        Assert.assertEquals(BookingStepDefs.requestBody.getLastname(), bookingDetails.get("lastname"), "Lastname mismatch!");
        Assert.assertEquals(BookingStepDefs.requestBody.getTotalprice(), bookingDetails.get("totalprice"), "Total price mismatch!");
        Assert.assertEquals(BookingStepDefs.requestBody.isDepositpaid(), bookingDetails.get("depositpaid"), "Deposit paid mismatch!");
        Assert.assertEquals(BookingStepDefs.requestBody.getAdditionalneeds(), bookingDetails.get("additionalneeds"), "Additional needs mismatch!");

        // Validate booking dates
        Map<String, String> bookingDatesMap = (Map<String, String>) bookingDetails.get("bookingdates");
        Assert.assertEquals(BookingStepDefs.requestBody.getBookingdates().getCheckin(), bookingDatesMap.get("checkin"), "Check-in date mismatch!");
        Assert.assertEquals(BookingStepDefs.requestBody.getBookingdates().getCheckout(), bookingDatesMap.get("checkout"), "Check-out date mismatch!");

        logger.info("Booking details validation successful!");
    }

    // 🔹 Helper Method: Extracts booking IDs from JSON response
    private List<Integer> extractBookingIds(Response response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Integer>> bookings = objectMapper.readValue(response.getBody().asString(),
                    new TypeReference<List<Map<String, Integer>>>() {});

            logger.debug("Extracted raw booking data: {}", bookings);
            return bookings.stream().map(booking -> booking.get("bookingid")).collect(Collectors.toList());
        } catch (JsonProcessingException e) {
            logger.error("Error parsing booking ID response!", e);
            throw new RuntimeException("Failed to parse booking IDs from response", e);
        }
    }
}
