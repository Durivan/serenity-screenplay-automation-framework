Feature: API examples

  @run
  Scenario: POST
    Given the "actor" is pointing to reqres.in
    When he creates an user
      | User name   | Carlos   |
      | User gender | male     |
      | User email  | random   |
      | User status | inactive |
    Then he should see the expected response of the create user service


  Scenario: GET
    Given the "actor" is pointing to reqres.in
    When he consult to the service for a random user
    Then he should see that there aren't any empty field on the response
    And the structure of the response is correct
