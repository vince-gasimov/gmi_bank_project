Feature:User info segment (User Settings) sho uld be editable on Homepage
@us0601
  Scenario Outline:US_06_TC_01There should be user info being populated when navigating to user info

  When user navigates to "Sign in" page under account menu icon
  And user types and submit the valid "<user>" credentials







  Examples:
    | user     |
    | admin    |
