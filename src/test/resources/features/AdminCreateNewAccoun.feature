Feature:System should allow Admin to create a new Account

  @us19
  Scenario:US_19_TC01User should create a description for the new account and it cannot be blank

    When user navigates to "Sign in" page under account menu icon
    And user types and submit the valid "admin" credentials
    And user navigates to "Manage Accounts" page under My Operations menu
    Then user take " You are not authorized to access this page." as alert message
