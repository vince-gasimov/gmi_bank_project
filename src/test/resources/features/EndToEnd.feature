Feature: End to end test for a customer with database and API validation
#there will be end to end scenario. It will include:
  #customer registration
    #registration database validation
    #registration API validation
  #employee account creation for this customer
    #accounnt database validation
    #account API validation
  #employee customer approve
    #database approval validation
    #api approval validation
  #admin customer activation
    #database activation validation
    #API activation validation
      #Maybe, money transfer can be added
  #print out all customers that we created
  @wip @db
  Scenario: End to end test for a customer with database and API validation
    When a customer candidate register itself with valid information on registration page
    Then verify the newregistered customer registration information directly from database
    And verify the newregistered customer registration information with the help of API
    When after successful "registration" operation of this customer, an "employee" sign in to the system
    And employee creates two different accounts for this customer with a description of its username
    Then verify the newcreated account information directly from database
    And verify the newcreated account information with the help of API
    When the employee approves the customer application by filling out additional information and binding created accounts
    Then verify the approved customer information directly from database
    And verify the approved customer information with the help of API
    When after successful "approval" operation of this customer, an "admin" sign in to the system
    And the admin activate the customer as a customer in the system
    Then verify that customer can sign in to the system with its valid credentials
    And verify that the customer can see its own accounts
    And verify the activated customer information directly from database
    And verify the activated customer information with the help of API
    And the all customers' information is printed out

