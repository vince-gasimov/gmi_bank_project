Feature: user should be able to change password so long as it does not match previous one
  Scenario:US_008 TC009 Verify that user can change password with a new one, not matching previuos one
    Given user sign in to the system as "customer"
    When user navigates to "Password" page under account menu icon
    And user types all information, including random generated password, and click to save button inside password_renew_page



