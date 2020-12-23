
Feature: System should allow the Admin to manage users


  Scenario: Verify that admin can activate a user role
    Given user sign in to the system as "admin"
    And user navigates to "User management" page under "Administration" menu
    When admin changes activation status of a randomly selected user
    Then verify that such an information message "A user is updated with" is appeared after operation on users_page
    And verify that activation status has changed

  @activation @wip
  Scenario Outline: Verify that admin can activate users with different roles by retrieving required information from excel "<userType>"

    Given user sign in to the system as "admin"
    And user navigates to "User management" page under "Administration" menu
    And find the user taken from excel and activate it as "<userType>"
    Then verify that such an information message "A user is updated with" is appeared after operation on users_page
    And update excel file by moving user from registered page to jhi page

    Examples:
      | userType |
      | admin    |
      | user     |
      | employee |

  Scenario: Admin can delete a user taken from excel
    Given user sign in to the system as "admin"
    And user navigates to "User management" page under "Administration" menu
    And take a user from excel sheet name "jhi_user" and delete it on users_page
    Then verify that such an information message "A user is deleted with" is appeared after operation on users_page
    And update excel file by removing user from "jhi_user" sheet


  Scenario: Admin can edit a user information taken from excel
    Given user sign in to the system as "admin"
    And user navigates to "User management" page under "Administration" menu
    And take a user from excel sheet name "jhi_user" and edit its on users_page
    Then verify that such an information message "A user is updated with" is appeared after operation on users_page
    And update excel file by editing user information inside "jhi_user" sheet