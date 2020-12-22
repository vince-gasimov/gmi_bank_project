Feature: Validate all data performing database testing

  @wip @activation @db
  Scenario Outline: verify that user type users' information which are retrieved from UI match the users' information which are retrieved from database "<userType>"

    Given user sign in to the system as "admin"
    And user navigates to "User management" page under "Administration" menu
    And find the user taken from excel and activate it as "<userType>"
    Then verify that UI information matches database response
    Examples:
      | userType |
      | admin    |
      | user     |
      | employee |

