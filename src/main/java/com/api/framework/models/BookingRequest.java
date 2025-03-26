package com.api.framework.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingRequest {

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("totalprice")
    private int totalprice;

    @JsonProperty("depositpaid")
    private boolean depositpaid;

    @JsonProperty("bookingdates")
    private BookingDates bookingdates;

    @JsonProperty("additionalneeds")
    private String additionalneeds;

    BookingRequest(BookingRequestBuilder builder) {
        this.firstname = builder.getFirstName();
        this.lastname = builder.getLastName();
        this.totalprice = builder.getTotalPrice();
        this.depositpaid = builder.isDepositPaid();
        this.bookingdates = builder.getBookingDates();
        this.additionalneeds = builder.getAdditionalNeeds();
    }

    // Getter methods (used for serialization)
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public int getTotalprice() { return totalprice; }
    public boolean isDepositpaid() { return depositpaid; }
    public BookingDates getBookingdates() { return bookingdates; }
    public String getAdditionalneeds() { return additionalneeds; }
}
