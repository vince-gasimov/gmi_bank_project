@All
Feature: Password rules

  Background: user navigates to Register page
    Given user navigates to "Register" page under account menu icon

  Scenario Outline: US_003 TC_001 verifying that not able to login with 1-3 chars
    When user types a "newpassword" "<password>" and press TAB
    Then verify that a message is displayed such as "Your password is required to be at least 4 characters."

    Examples:
      | password |
      | Q12      |
      | wer      |
      | **       |
      | A        |


  Scenario Outline: US_003 TC_002 verifying that  able to login with 4-6 chars without error and with 1 light
    When user types a "newpassword" "<password>" and press TAB
    Then verify that no message is displayed
    And verify that 1 led is lighting

    Examples:
      | password |
      | As12     |
      | a12*w    |
      | *12.)O   |


  Scenario Outline: US_003 TC_003 verifying that  able to login with >= 7 chars (composed of 4 different types) without error and with 5 light
    When user types a "newpassword" "<password>" and press TAB
    Then verify that no message is displayed
    And verify that 5 led is lighting

    Examples:
      | password     |
      | As12*er      |
      | a12*wQ.nkvns |
      | *12.)O 1Qq   |


  Scenario Outline: US_003 TC_004 Verify that password having more than or equal 7 chars and chars, composed of 3 different types, does not generate error message and shows 4 lights after providing all required information
    When user types a "newpassword" "<password>" and press TAB
    Then verify that no message is displayed
    And verify that 4 led is lighting

    Examples:
      | password |
      | As12345  |
      | a*)ert2  |
      | 12)/QER  |


  Scenario Outline: US_003 TC_005 Verify that password having more than or equal 7 chars and chars, composed of 2 different types, does not generate error message and shows 4 lights after providing all required information
    When user types a "newpassword" "<password>" and press TAB
    Then verify that no message is displayed
    And verify that 2 led is lighting

    Examples:
      | password      |
      | Asdjfkjssvs   |
      | a*)ancna)))__ |
      | 12QDCSVSVSSVS |


  Scenario Outline: US_003 TC_006 Verify that password having more than or equal 7 chars and chars, composed of 1 different types, does not generate error message and shows 4 lights after providing all required information
    When user types a "newpassword" "<password>" and press TAB
    Then verify that no message is displayed
    And verify that 1 led is lighting

    Examples:
      | password       |
      | ADFSDSSSGSG    |
      | sdkjsjsnvnskmn |
      | 01233456       |


  Scenario: US_003 TC_007 Verify that password having 1-3 chars is not accepted even all other information is valid.
    When Type all valid information into following textboxes with values in the registration page
      | address           | ajfkha 80 57836 Mah.    |
      | firstname         | emre                    |
      | lastname          | cihan                   |
      | ssn               | 111-55-1310             |
      | mobilephonenumber | 111-222-3333            |
      | username          | emre12                  |
      | email             | emre@gmail.comfsddsgfsf |
    And user types a "newpassword" "123" and press TAB
    And user types a "newpasswordconfirmation" "123" and press TAB
    And click to register in the registration page
    Then verify that new user is not registered

  @wip
  Scenario: a new user should be able to register to the system with a new valid SSN and a valid password with length of 7 chars
    When user types following information and click to register button
      | ssnNumber         |
      | firstName         |
      | lastName          |
      | address           |
      | mobilePhoneNumber |
      | userName          |
      | email             |
    Then verify that new user is registered by getting such a message "Registration saved!"

