Feature: User Authentication and Account Management

  Background:
    Given the user navigates to the OpenCart home page

  @Registration @Positive
  Scenario: OC-R1 - Successful User Registration with valid data
    When the user navigates to Register page
    And the user enters first name "John", last name "Doe", and email "john.doe.test@example.com"
    And the user enters password "SecurePass123!" and agrees to privacy policy
    And the user clicks Continue button
    Then the account should be created successfully

  @Registration @Negative
  Scenario: OC-R1 - Duplicate registration prevention
    When the user navigates to Register page
    And the user enters first name "John", last name "Doe", and email "john.doe.test@example.com"
    And the user enters password "SecurePass123!" and agrees to privacy policy
    And the user clicks Continue button
    Then a warning message "Warning: E-Mail Address is already registered!" should be displayed

     @Registration @Validation
  Scenario Outline: OC-R1 - Mandatory field validation check
    When the user navigates to Register page
    And the user enters first name "<firstname>", last name "<lastname>", and email "<email>"
    And the user enters password "<password>"
    And the user clicks Continue button
    Then the corresponding error message "<error_msg>" should be shown

    Examples:

      | firstname | lastname | email             | password    | error_msg                                    |
      |           | Smith    | am@example.com    | Pass123!    | First Name must be between 1 and 32 characters!  |
      | Alex      |          | am@example.com    | Pass123!    | Last Name must be between 1 and 32 characters!   |
      | Alex      | Smith    | invalid-email     | Pass123!    | E-Mail Address does not appear to be valid!  |
      | Alex      | Smith    | am2@example.com   | 123         | Password must be between 4 and 20 characters!|

 @Login @Positive
  Scenario: OC-R2 - Successful user login and logout
    When the user navigates to Login page
    And the user logs in with valid email "john.doe.test@example.com" and password "SecurePass123!"
    Then the user should be redirected to the Account dashboard page
    When the user clicks the Logout link
    Then the user should be logged out successfully

