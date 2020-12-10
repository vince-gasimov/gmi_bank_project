Feature: Employee creates a new customer
  @wip
  Scenario: employee is able to search new customer with SSN in the create customer page
    Given user sign in to the system as "employee"
    When user navigates to "Manage Customers" page under My Operations menu
    And user click Create_New_Customer button under customer_page
    And user types ssn number and click search_button
    Then the create_or_edit_customer page is populated and getting successful message









