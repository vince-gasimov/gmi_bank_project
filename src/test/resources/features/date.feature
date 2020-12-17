Feature:Date enter and Account Manage

 Background:An Employee sing in
   Given user navigates to "Sign in" page under account menu icon
    When user types and submit the valid "employee" credentials

  @us1401abc
  Scenario:US_14_TC01The date cannot be typed earlier, in the past, at the time of creation an account
    When user navigates to "Manage Accounts" page under My Operations menu
    And user clicks on the create a new Account item
    And user enters  description "MyNewAccount"
    And  user enters "1000" on balanceBox
    And User chooses Account Type "CHECKING" dropDown
    And user chooses Account status "ACTIVE" dropDown
    Then user must be alerted if a date "Date" in the past is entered
    And User must be take if a invalid format Date the alert

