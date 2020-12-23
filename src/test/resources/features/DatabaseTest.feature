Feature: Validate all data performing database testing

    @db
  Scenario Outline: verify that user information from db and excel match "<userType>"
    Given take randomly a user with role "<userType>" from excel sheet "jhi_user"
    When retrieve same user from database
    Then verify that information retrieved from database and excel match

    Examples:
    |userType|
    |admin   |
    |employee|
    |user    |
   @db
    Scenario: verify that UI countries match database countries
      Given user sign in to the system as "employee"
      And user navigates to "Manage Customers" page under My Operations menu
      And user navigates to new customer creation page and gets all countries
      When retrieve all countries from database
      Then verify that UI countries match database countries

      