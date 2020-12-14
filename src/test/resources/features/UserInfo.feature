@All
Feature:User info segment (User Settings) sho uld be editable on Homepage

  @us0601

  Scenario Outline:US_06_TC_01There should be user info being populated when navigating to user info

    When user navigates to "Sign in" page under account menu icon
    And user types and submit the valid "<user>" credentials
    And "Home" page should be displayed with user information beside icon
    And user navigates user  account dropdown menu icon
    Then  user checks to information
    Then user  clicks on language  drop-down and chooses one of the English and Turkish language

    Examples:user
      | user     |
      | employee  |


  @us0602
  Scenario Outline:US_06_TC_03_05information update
    When user navigates to "Sign in" page under account menu icon
    And user types and submit the valid "<user>" credentials
    And user navigates user  account dropdown menu icon

    And user should click on firstname button and can update
    And user should click on lastname button and can update
    And  user should click on e-mail button and can update
    Then user clicks save button


    Examples:user
      | user     |
      | admin    |

  @us0605negative01
  Scenario Outline:US_06_TC05update mit invalid email
    When user navigates to "Sign in" page under account menu icon
    And user types and submit the valid "<user>" credentials
    And user navigates user  account dropdown menu icon

    And user shouldn't to be update  "<invalidEmail>" the end

    Examples:user and invalid Email
      | user       |invalidEmail|
      | admin      |abc.com     |



  @us0605negative02
  Scenario Outline:US_06_TC05update mit invalid email
    When user navigates to "Sign in" page under account menu icon
    And user types and submit the valid "<user>" credentials
    And user navigates user  account dropdown menu icon

    And user shouldn't to be update  "<invalidEmail>" the end

    Examples:user and invalid Email
      | user       |invalidEmail|
      | employee    |abc@gl     |



  @us0603
  Scenario Outline:US_06_TC01User check to update information
    When user navigates to "Sign in" page under account menu icon
    And user types and submit the valid "<user>" credentials
    And user navigates user  account dropdown menu icon
    Then user checks to update information


    Examples:
      | user     |
      | admin    |









