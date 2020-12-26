@applicant
Feature: Read All Applicants

  @US_023 @wip
  Scenario: System should allow to read applicants
    Given get all applicants
    And verify that all applicants data
    Then verify that data of applicant