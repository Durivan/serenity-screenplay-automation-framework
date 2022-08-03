Feature: API examples

  @run
  Scenario: POST
    Given the "actor" is pointing to reqres.in
    When he creates an user
      | FirstName | Job     |
      | Carlos    | Soporte |
    Then he should see the expected response of the create user service

  @run
  Scenario: GET
    Given the "actor" is pointing to reqres.in
    When he consult to the service for a random user
    Then he should see that there aren't any empty field on the response
    And the structure of the response is correct
