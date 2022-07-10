Feature: deneme

  Scenario:US_015 TC001 As a Customer User should see all account types and balance populated
    Given user sign in to the system as new Customer
    When  user navigates to "My Accounts" under the My Operations menu
    And verify that visible titles such as Balance and AccountType
  @acco
    Scenario:US_015 TC002 As a Customer User should see transaction
      Given user sign in to the system as new Customer
      When user navigates to "My Accounts" under the My Operations menu
      And  verify that a view Transaction and clickable and visible



