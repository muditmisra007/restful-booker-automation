Feature: Booking API

  Background:

  Scenario: Create a new booking
    Given I have a valid booking payload
    When I send a POST request to create booking
    Then I should receive a response with status code 200

