Feature: Validate all data performing database testing
  Scenario: verify that user type users' information which are retrieved from UI match the users' information which are retrieved from database

    Given sign in to the system as "admin"
    And navigate to "User management" page under "Administration" menu
    And find the user taken from excel and activate it as "admin"
    Then verify that UI information matches database response
