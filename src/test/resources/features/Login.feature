Feature: Login Functionality

  Background:
    Given user navigates to "Sign in" page under account menu icon

  Scenario Outline: verify login functionality for all user type with valid info
    When user types and submit the valid "<user>" credentials
    Then "Home" page should be displayed with user information beside icon

    Examples:
      | user     |
      | admin    |
      | customer |
      | employee |

    Scenario: US_004 TC_002 There should be an option to cancel login
      And user click cancel button
      Then verify that user is on the home page without sign in to system


    Scenario: US_005 TC_001 User cannot login with invalid username or valid password
      When user types invalid username "username"
      And user types valid password
      And user click to sign in button
      Then verify that login operation failed

  Scenario: US_005 TC_002 User cannot login with valid username or invalid password
    When user types valid username
    And user types invalid password "password"
    And user click to sign in button
    Then verify that login operation failed

  Scenario: US_005 TC_003 User cannot login with invalid username or invalid password
    When user types invalid username "username"
    And user types invalid password "password"
    And user click to sign in button
    Then verify that login operation failed

   Scenario: US_005 TC_004 User with invalid credentials should be given an option to reset their password
        When user click on the forget password link
        Then verify that user navigate to forgot password page

  Scenario: US_005 TC_006 User should be given the option to navigate to registration page if they did not register yet
      And user click on the register a new account link
      Then verify that user navigate to registration page

  Scenario Outline: US_007 TC_001 System should not allow to make updates with invalid credentials.
    Given user sign in to the system as "customer"
    When user navigates to "User Info" page under account menu icon
    And user types a valid "Firstname" and "Lastname" pres TAB
    And Invalid "<input>" entered to "<credentials>" box user see "<Error Message>" displayed
    Examples: TC_001 Email id cannot contain just digits or chars without "@" sign or ".com" extension
      | credentials |  input          | Error Message         |
      | email | @                     | This field is invalid |
      | email | group.com             | This field is invalid |
      | email | @group.com            | This field is invalid |
      | email | employee@group16.com  | Settings saved!       |