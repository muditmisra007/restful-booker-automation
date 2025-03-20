@RunHooks
Feature: Create Booking API
  Scenario Outline: Successfully create a new booking
    Given I create a new booking with the following details:
      | firstname       | <firstname>       |
      | lastname        | <lastname>        |
      | totalprice      | <totalprice>      |
      | depositpaid     | <depositpaid>     |
      | checkin         | <checkin>         |
      | checkout        | <checkout>        |
      | additionalneeds | <additionalneeds> |
    Then the booking should be created successfully

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Alice     | Smith    | 200        | false       | 2025-06-10 | 2025-06-20 | Breakfast       |
      | John      | Doe      | 150        | true        | 2025-07-01 | 2025-07-05 | Lunch           |
