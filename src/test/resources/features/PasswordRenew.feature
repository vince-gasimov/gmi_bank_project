
Feature: user should be able to change password so long as it does not match previous one

  Background:
    Given user sign in to the system as "dynamic_customer"
    And user navigates to "Password" page under account menu icon


  Scenario:US_008 TC_001 Verify that user can change password with a new one, not matching previous one
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

  Scenario Outline: US_008 TC_003 verifying that user able to change password with length 4-6 chars without error and with 1 light
    When user types "<password>" inside textBox "new_password" on password_renew_page
    Then verify that no error message under password textbox is displayed and 1 led lighting
    Examples:
      | password |
      | As12     |
      | a12*w    |
      | *12.)O   |

  Scenario Outline: US_008 TC_004 verifying that user able to change password with >= 7 chars (composed of 4 different types) without error and with 5 light
    When user types "<password>" inside textBox "new_password" on password_renew_page
    Then verify that no error message under password textbox is displayed and 5 led lighting
    Examples:
      | password     |
      | As12*er      |
      | a12*wQ.nkvns |
      | *12.)O 1Qq   |

  Scenario Outline: US_008 TC_005 Verify that user able to change with >= 7 chars (composed of 3 different types), does not generate error message and shows 4 lights after providing all required information
    When user types "<password>" inside textBox "new_password" on password_renew_page
    Then verify that no error message under password textbox is displayed and 4 led lighting
    Examples:
      | password |
      | As12345  |
      | a*)ert2  |
      | 12)/QER  |

  Scenario Outline: US_008 TC_006 Verify that user able to change with >= 7 chars ,composed of 2 different types, does not generate error message and shows 2 lights after providing all required information
    When user types "<password>" inside textBox "new_password" on password_renew_page
    Then verify that no error message under password textbox is displayed and 2 led lighting
    Examples:
      | password      |
      | Asdjfkjssvs   |
      | a*)ancna)))__ |
      | 12QDCSVSVSSVS |

  Scenario Outline: US_008 TC_007 Verify that user able to change with >= 7 chars,composed of 1 different types, does not generate error message and shows 1 lights after providing all required information
    When user types "<password>" inside textBox "new_password" on password_renew_page
    Then verify that no error message under password textbox is displayed and 1 led lighting
    Examples:
      | password       |
      | ADFSDSSSGSG    |
      | sdkjsjsnvnskmn |
      | 01233456       |


  Scenario: US_008 TC_008 Verify that user cannot change password by trying to enter a different one into current password textbox as current password
    When user types a different password as current password and new generated password into new password textbox and then click to save button inside password_renew_page
    Then verify that a message is displayed such as "An error has occurred! The password could not be changed." inside password_renew_page

  Scenario: US_008 TC_009 Verify that new password confirmation works
    When user types "denemeNewPassword" into new password textBox and "differentConfirmation" into new password confirmation textbox
    Then verify that a message is displayed such as "The password and its confirmation do not match!" on password_renew_page

  Scenario:US_008 TC_010 Verify that user should not be able to change password to the previous one
    When user change password with a new one "ismail" and change again with old one
    Then verify that a message is displayed such as "An error has occurred! The password could not be changed." inside password_renew_page

