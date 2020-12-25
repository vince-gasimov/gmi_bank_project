Feature:Money transfer should be available

  Scenario:US_016 TC001 As a customer user can not money transfer with one account
    Given user sign in to the system as new Customer1
    When  user navigates to "Transfer Money" under the My Operations menu
    And  verify that user can not transfer money with one account

  Scenario:US_016 TC002 As a customer can money transfer with least 2 accounts
    Given user sign in to the system as new Customer
    When  user navigates to "Transfer Money" under the My Operations menu
    And  verify that user can transfer money least two accounts


    Scenario: US_016 T003 As a customer when user has one account ,there is no account to
    select on the TO Dropdown and gets warning alert around the TO Dropdown
      Given user sign in to the system as new Customer
      When  user navigates to "Transfer Money" under the My Operations menu
      And  verify that user can not see any account on the TO Dropdown

  Scenario:US_016 TC004 As a customer user should see at least two accounts FROM Dropdown
    Given user sign in to the system as new Customer
    When  user navigates to "Transfer Money" under the My Operations menu
    And  verify that user should see two accounts on the FROM Dropdown

  Scenario:US_016 TC005 As a customer when user doesn't enter a value on the balance,
  gets a warning This field is required
    Given user sign in to the system as new Customer
    When  user navigates to "Transfer Money" under the My Operations menu
    And  verify that user should get warning message under the balance textbox

  Scenario Outline:US_016 TC006 As a customer when user doesn't enter invalid values on the balance
  get a warning message only numbers max 5 digits
    Given user sign in to the system as new Customer
    When  user navigates to "Transfer Money" under the My Operations menu
    And   user types "<invalidbalance>" inside balance textbox
    And  verify that user should get warning message under the balance textbox
    Examples:
      | invalidbalance |
      | Q12      |
      | -23A      |
      | 344423   |
      | -456     |


  Scenario Outline:US_016 TC007 As a customer when user doesn't enter a value on the balancecent,
  gets a warning message only numbers max 2 digits
    Given user sign in to the system as new Customer
    When  user navigates to "Transfer Money" under the My Operations menu
    And   user types "<invalidbalancecent>" inside balancecent textbox
    And  verify that user should get warning message under the balancecent textbox
  Examples:
      | invalidbalancecent |
      | Q12      |
      | -23      |
      | 344      |
      | -1A      |


  Scenario:US_016 TC008 As a customer when user doesn't write anything on the Description
  TextBox,gets a warning This field is required
    Given user sign in to the system as new Customer
    When  user navigates to "Transfer Money" under the My Operations menu
    And  verify that user should get warning message under the description textbox

  @money
  Scenario:US_016 TC09 As a customer when user write valid values,gets success message
    Given user sign in to the system as new Customer
    When  user navigates to "Transfer Money" under the My Operations menu
    And  verify that user should get success message left side


