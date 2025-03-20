Feature: Get Booking Details

  Scenario: Retrieve all booking IDs
    When I send a request to get all bookings
    Then the response should return a list of booking IDs
    And the status code should be 200


  Scenario Outline: Validate the data against a booking id
    Given I create a new booking with the following details:
      | firstname       | <firstname>       |
      | lastname        | <lastname>        |
      | totalprice      | <totalprice>      |
      | depositpaid     | <depositpaid>     |
      | checkin         | <checkin>         |
      | checkout        | <checkout>        |
      | additionalneeds | <additionalneeds> |
    Then the booking should be created successfully
    When I send a request with booking id
    Then the status code should be 200
    And the response should return the booking details
    And the returned booking details must be same as booking creation details

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Alice     | Smith    | 200        | false       | 2025-06-10 | 2025-06-20 | Breakfast       |
      | John      | Doe      | 150        | true        | 2025-07-01 | 2025-07-05 | Lunch           |