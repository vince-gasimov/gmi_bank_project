@US01
Feature:  System should allow any user to register with valid credentials validating the success message

  Background:
    Given user navigates to "Register" page under account menu icon

  @US01TC01
  Scenario Outline: US_001 TC_001 There should be a valid SSN respecting the "-" where necessary, it should be 9 digits long
    When user types a "ssn" "<SSN Number>" and press TAB
    Then verify that no message is displayed

    Examples: Test data for SSN
      | SSN Number  |
      | 092-01-0813 |
      | 123-00-1345 |


  @US01TC02 @wip
  Scenario Outline: US_001 TC_002 There should be a valid name that contains chars
    Given user types a "firstname" "<First Name>" and press TAB
    Then verify that no message is displayed
    Examples:
      | First Name |
      | Betul      |