package com.api.framework.models;

public class BookingRequestBuilder {
    private String firstName;
    private String lastName;
    private int totalPrice;
    private boolean depositPaid;
    private BookingDates bookingDates;
    private String additionalNeeds;

    public BookingRequestBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public BookingRequestBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BookingRequestBuilder setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public BookingRequestBuilder setDepositPaid(boolean depositPaid) {
        this.depositPaid = depositPaid;
        return this;
    }

    public BookingRequestBuilder setBookingDates(BookingDates bookingDates) {
        this.bookingDates = bookingDates;
        return this;
    }

    public BookingRequestBuilder setAdditionalNeeds(String additionalNeeds) {
        this.additionalNeeds = additionalNeeds;
        return this;
    }

    public BookingRequest build() {
        return new BookingRequest(this);
    }

    // Getters for the main class constructor
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getTotalPrice() { return totalPrice; }
    public boolean isDepositPaid() { return depositPaid; }
    public BookingDates getBookingDates() { return bookingDates; }
    public String getAdditionalNeeds() { return additionalNeeds; }
}
