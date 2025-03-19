package com.api.framework.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingDates {
    @JsonProperty("checkin")
    private String checkin;

    @JsonProperty("checkout")
    private String checkout;

    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    // Update getter names to exactly match JSON property names
    public String getCheckin() { return checkin; }
    public String getCheckout() { return checkout; }
}
