Feature:User info segment (User Settings) sho uld be editable on Homepage
@us0601
  Scenario Outline:US_06_TC_01There should be user info being populated when navigating to user info

  When user navigates to "Sign in" page under account menu icon
  And user types and submit the valid "<user>" credentials
  And "Home" page should be displayed with user information beside icon
 And user navigates user  account dropdown menu icon
  Then  user checks to information
  Then user  clicks on language  drop-down and chooses one of the English and Turkish language






  Examples:
    | user     |
    | admin    |





