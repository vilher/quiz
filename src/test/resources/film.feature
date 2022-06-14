Feature: Film REST endpoint
  Scenario: Fetch all film list by REST endpoint
    When The user sends GET request to the endpoint
    Then The HTTP status is OK
    And The film list is not empty