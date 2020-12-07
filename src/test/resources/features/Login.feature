Feature: Login Functionality

  @wip
  Scenario Outline: verify login functionality for all user type with valid info

    When user navigates to "Sign in" page under account menu icon
    And user types and submit the valid "<user>" credentials
    Then "Home" page should be displayed with user information beside icon

    Examples:
      | user     |
      | admin    |
      | customer |
      | employee |


