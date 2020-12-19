Feature: Employee can manage customer

  Scenario: Employee can see user information in a table with specified columns
    Given user sign in to the system as "employee"
    When user navigates to "Manage Customers" page under My Operations menu
    Then verify that a table is displayed in the Customers_page with following columns
      | First Name          |
      | Last Name           |
      | Middle Initial      |
      | Email               |
      | Mobile Phone Number |
      | Phone Number        |
      | Address             |
      | Create Date         |
  @wip
  Scenario: Employee can see user information in their own page after clicking view button
    Given user sign in to the system as "employee"
    When user navigates to "Manage Customers" page under My Operations menu
    Then verify that there is one "view" button for each row in the table
    And verify that after clicking "view" button for a user with a randomly selected email in the page, user information is populated
    And verify that in the user page, there is an edit button