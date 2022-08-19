Feature: API examples


  Scenario: Create a new user and validate the integrity of the data that was sent
    Given the "Manager" is pointing to gorest.co.in
    When he creates an user
      | User name   | Carlos   |
      | User gender | male     |
      | User email  | random   |
      | User status | inactive |
    Then he should see that the service has returned the same data that was sent on user creation


  @run
  Scenario: Update an user
    Given the "Manager" is pointing to gorest.co.in
    And he has created an user
      | User name   | Carlos   |
      | User gender | male     |
      | User email  | random   |
      | User status | inactive |
    When he updates the user data
      | User name   | Juan   |
      | User status | active |
    And he consults for the user edited
    #Then he should see that the user was updated correctly


    #When he consult to the service for a random user
