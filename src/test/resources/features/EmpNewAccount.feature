@Ahmet
Feature:User can create a new Account

  Background:An Employee sing in
    Given user navigates to "Sign in" page under account menu icon
    When user types and submit the valid "employee" credentials

  @us1301
  Scenario:US_13_TC_01User should create a description for the new account and it cannot be blank
    When user navigates to "Manage Accounts" page under My Operations menu
    And user clicks on the create a new Account item
    And user enters  description "MyNewAccount"
    Then If user leaves the description blank, he should receive an alert


  @us1302a
  Scenario:US_13_TC02User should provide a balance for the first time account creation as Dollar
    When user navigates to "Manage Accounts" page under My Operations menu
    And user clicks on the create a new Account item
    And user enters  description "MyNewAccounts"
    And  user enters "1000" on balanceBox

  @us1302b
  Scenario:US13_TC02B
    When user navigates to "Manage Accounts" page under My Operations menu
    And user clicks on the create a new Account item
    And user enters  description "MyNewAccounts"
    And If the user just clicks on the balance box and no value is entered, the alert will be
    Then the user receives the alert if they click the save button without clicking the balance box


  @us1302negative
  Scenario Outline:US_13_TC02User should provide a balance for the first time account creation as Dollar
    When user navigates to "Manage Accounts" page under My Operations menu
    And user clicks on the create a new Account item
    And user enters  description "MyNewAccounts"
    Then  if user enters "<invalidBalance>" ,the alert will be
    Examples:
      | invalidBalance |
      | a              |
      | .@_!           |
      | 1d0            |


  @us1303
  Scenario Outline:User can select an account type as CHECKING, SAVING, CREDIT_CARD or INVESTING
    When user navigates to "Manage Accounts" page under My Operations menu
    And user clicks on the create a new Account item
    And User chooses Account Type "<Account Type>" dropDown

    Examples:
      | Account Type |
      | CHECKING     |
      | SAVING       |
      | CREDIT_CARD  |
      | INVESTING    |

  @us1304
  Scenario Outline:US_13_TC04Account status should be defined as ACTIVE, SUSPENDED or CLOSED
    When user navigates to "Manage Accounts" page under My Operations menu
    And user clicks on the create a new Account item
    And user chooses Account status "<Account Status>" dropDown

    Examples:
      | Account Status |
      | ACTIVE         |
      | SUESPENDED     |
      | CLOSED         |


  @us1305
  Scenario:US_13_TC05User can select an employee from the drop-down
    When user navigates to "Manage Accounts" page under My Operations menu
    And user clicks on the create a new Account item
    And user clicks on employee dropdown item
