Feature: Password rules

  Scenario Outline: US_003 TC_001 verifying that not able to login with 1-3 chars
    When user navigates to "Register" page under account menu icon
    And user types a password "<password>" and press TAB
    Then verify that a message is displayed such as "Your password is required to be at least 4 characters."

    Examples:
      | password |
      | Q12      |
      | wer      |
      | **       |
      | A        |


  Scenario Outline: US_003 TC_002 verifying that  able to login with 4-6 chars without error and with 1 light
    When user navigates to "Register" page under account menu icon
    And user types a password "<password>" and press TAB
    Then verify that no message is displayed
    And verify that 1 led is lighting

    Examples:
      | password |
      | As12     |
      | a12*w    |
      | *12.)O   |

  @wip
  Scenario Outline: US_003 TC_003 verifying that  able to login with >= 7 chars (composed of 4 different types) without error and with 5 light
    When user navigates to "Register" page under account menu icon
    And user types a password "<password>" and press TAB
    Then verify that no message is displayed
    And verify that 5 led is lighting

    Examples:
      | password     |
      | As12*er      |
      | a12*wQ.nkvns |
      | *12.)O 1Qq   |



