Feature: Order Checkout Functionality

  @Checkout @Positive
  Scenario: OC-C1 - Verify a guest user can complete the checkout process without creating an account
    Given the user navigates to the OpenCart home page
    When the user searches for a product "HP"
    And the user adds the product to the shopping cart
    And the user navigates to the checkout page
    And the user provides billing details "Ankit", "Surthar", "ankit@example.com", "234", "234", "Jodhpur", "344026"
    And the user confirms the shipping and payment methods
    And the user clicks the Final Confirm Order button
    Then the order should be placed successfully with confirmation message "Your order has been placed!"