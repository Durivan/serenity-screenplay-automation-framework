Feature: Make a purchase

  @smoke
  Scenario: Validate the successful purchase of a random product
    Given the user is on the login page
    And he signs in with the following credentials: "standard_user", "secret_sauce"
    When he adds to the cart a random item from the product page
    And completes the checkout form with the data
      | Firstname | aaa |
      | Lastname  | bbb |
      | ZipCode   | 123 |
    Then he should see on the purchase congrats page the following text: "order has been dispatched"
