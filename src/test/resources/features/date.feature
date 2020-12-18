Feature:Date enter and Account Manage

  Background:An Employee sing in
    Given user navigates to "Sign in" page under account menu icon
    When user types and submit the valid "employee" credentials

  @us1401abc @bug
  Scenario:US_14_TC01The date cannot be typed earlier, in the past, at the time of creation an account
    When user navigates to "Manage Accounts" page under My Operations menu
    And user clicks on the create a new Account item
    And user enters  description "MyNewAccount"
    And  user enters "1000" on balanceBox
    And User chooses Account Type "CHECKING" dropDown
    And user chooses Account status "ACTIVE" dropDown
    Then user must be alerted if a date "Date" in the past is entered

  @us140333 @bug
  Scenario Outline: The date should be created as month, day, year, hour and minute
    When user navigates to "Manage Accounts" page under My Operations menu
    And user clicks on the create a new Account item
    And user enters  description "MyNewAccount"
    And  user enters "1000" on balanceBox
    And User chooses Account Type "CHECKING" dropDown
    And user chooses Account status "ACTIVE" dropDown
    Then if user enters invalid datetype "<InvalidDateType>"  then get alert
    Examples:
      | InvalidDateType  |
      | 20.12.2020,22:20 |
      | 2020.12.18,22:20 |
      | 2020.40.40,45:40 |

    @us14003
    Scenario:User can choose a user from the registration and it cannot be blank
      When user navigates to "Manage Accounts" page under My Operations menu
      And user clicks on the create a new Account item
      And user enters  description "MyNewAccount"
      And  user enters "1000" on balanceBox
      And User chooses Account Type "CHECKING" dropDown
      And user chooses Account status "ACTIVE" dropDown
      Then user can chooses a user from the registirition and can not be blank

  @us14004i
  Scenario:User can choose a user from the registration and it cannot be blank
    When user navigates to "Manage Customers" page under My Operations menu
    Then user can chooses one of the Accounts
    And User can select Zelle Enrolled Optionally
