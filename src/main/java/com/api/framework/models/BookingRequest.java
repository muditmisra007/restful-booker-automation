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

    private BookingRequest(Builder builder) {
        this.firstname = builder.firstName;
        this.lastname = builder.lastName;
        this.totalprice = builder.totalPrice;
        this.depositpaid = builder.depositPaid;
        this.bookingdates = builder.bookingDates;
        this.additionalneeds = builder.additionalNeeds;
    }

    // Getter methods (used for serialization)
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public int getTotalprice() { return totalprice; }
    public boolean isDepositpaid() { return depositpaid; }
    public BookingDates getBookingdates() { return bookingdates; }
    public String getAdditionalneeds() { return additionalneeds; }

    // Builder Class (Setter method names remain unchanged)
    public static class Builder {
        private String firstName;
        private String lastName;
        private int totalPrice;
        private boolean depositPaid;
        private BookingDates bookingDates;
        private String additionalNeeds;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder setDepositPaid(boolean depositPaid) {
            this.depositPaid = depositPaid;
            return this;
        }

        public Builder setBookingDates(BookingDates bookingDates) {
            this.bookingDates = bookingDates;
            return this;
        }

        public Builder setAdditionalNeeds(String additionalNeeds) {
            this.additionalNeeds = additionalNeeds;
            return this;
        }

        public BookingRequest build() {
            return new BookingRequest(this);
        }
    }
}
