@state
Feature: Read Create Delete States

  @US_022
  Scenario: System should allow to read states
    Given get all states
    And verify that all states data
    Then verify that data of state id

  @US_024
  Scenario Outline: US_024 System allow the user to create a state
    Given user get data with valid token and "endpoint"
    Then user verify status code 200 and content type JSon
    And user create a new "<state>" one by one if it is not created already
    When validate "<state>" is created
    Examples: data
      | state |
      | LA    |
      | NJ    |
      | FL    |

  @US_027
  Scenario: US_027 TC01 User can just delete each state 1 by 1
    Given user deletes the state of a given id
    Then verifies the state he deleted