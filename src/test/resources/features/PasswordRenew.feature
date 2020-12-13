
Feature: user should be able to change password so long as it does not match previous one
  Background:
    Given user sign in to the system as "dynamic_customer"
    And user navigates to "Password" page under account menu icon


  Scenario:US_008 TC009 Verify that user can change password with a new one, not matching previous one
    When user types current password and new generated password and then click to save button inside password_renew_page
    Then verify that a message is displayed such as "Password changed!" inside password_renew_page

  Scenario Outline: US_008 TC_002 verifying that user not able to change password with length of 1-3 chars "<password>"
    When user types "<password>" inside textBox "new_password" on password_renew_page
    Then verify that a message is displayed such as "Your password is required to be at least 4 characters." on password_renew_page
    Examples:
      | password |
      | Q12      |
      | wer      |
      | **       |
      | A        |



  Scenario:US_008 TC010 Verify that user should not be able to change password to the previous one
      When user change password with a new one "ismail" and change again with old one
      Then verify that a message is displayed such as "An error has occurred! The password could not be changed." inside password_renew_page
    
