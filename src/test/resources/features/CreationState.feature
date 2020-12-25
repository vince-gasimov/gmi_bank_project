@state
Feature: US24 : Data Creations
  Scenario Outline: System allow the user to create a state using valid endpoint
    Given user get data with valid token and "endpoint"
    Then user verify status code 200 and content type JSon
    And user create a new "<state>" one by one if it is not created already
    When validate "<state>" is created
    Examples: data
      | state |
      | LA    |
      | NJ    |
      | FL    |