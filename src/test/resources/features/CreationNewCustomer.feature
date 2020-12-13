Feature: Employee creates a new customer

  Background:
    Given user sign in to the system as "employee"
    When user navigates to "Manage Customers" page under My Operations menu
    And user click Create_New_Customer button under customer_page

  Scenario: employee is able to search new customer with SSN in the create customer page
    And user types ssn number and click search_button
    Then the create_or_edit_customer page is populated and getting successful message


  Scenario: US_011 TC_001 The date cannot be typed earlier, in the past, at the time of creation a customer
    And user click date&time box
    And user types invalid date "12-11-20 03:45" and press tab key
    Then verify that invalid date is not accepted

  Scenario: US_011 TC_002 Date should be written at the time of customer creation
    And user click date&time box
    And user types valid date and press tab key
    Then verify that valid date is accepted

  Scenario: US_011 TC_003 The date should be created as month, day, year, hour and minute
    And user click date&time box
    And user types valid date form "MM-dd-yy hh:mm" and press tab key
    Then verify that valid date form is accepted

  Scenario: US_011 TC_004 The date should not be created as day. month, year, hour and minute
    And user click Date&Time box
    And user types invalid date form "dd-MM-yy hh:mm" and press tab key
    Then verify that valid date form is not accepted

  Scenario: US_011 TC_005 User can choose a user from the registration and it cannot be blank
    And user click User box
    And user select a User from the drop down menu
    Then verify that selected a User from the drop down menu

  Scenario: US_011 TC_006 User box cannot be blank
    And user click User box
    And user select blank option from the drop-down menu
    Then verify that not selected blank option from the drop-down menu

  Scenario: US_011 TC_007 There user can choose an account created on manage accounts
    And user click Account box
    And user select an account
    Then verify that selected an account

  Scenario: US_011 TC_008 User can select Zelle Enrolled optionally and save it
    And user click Zelle Enrolled check box and click Save button
    Then verify that clicked Zelle Enrolled check box and clicked Save button






