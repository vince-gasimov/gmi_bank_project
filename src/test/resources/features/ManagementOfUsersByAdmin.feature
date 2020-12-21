Feature: System should allow the Admin to manage users

  Scenario: Verify that admin can activate a user role
    Given user sign in to the system as "admin"
    And user navigates to "User management" page under "Administration" menu
    When admin changes activation status of a randomly selected user
    Then verify that such an information message "A user is updated with" is appeared after change of status operation
    And verify that activation status has changed

