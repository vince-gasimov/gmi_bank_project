@AllCustomerApi
Feature: Gmi Bank All Customer Info


  Scenario: US_020 Read and validate all customer data
    Given get all customers
    And verify that all customers data
    Then verify that data of customer id