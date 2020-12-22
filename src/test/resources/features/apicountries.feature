@CountryReader
Feature: Read countries

  @CreateCountry
  Scenario Outline: create a country
    Given user sets the response using api end point "https://www.gmibank.com/api/tp-countries" and creates country using "<idJson>" and "<nameJson>"
    Examples: create country
      | idJson | nameJson |
      | name   | Honolulu |

  Scenario: read all countries
    Given user sets the countries to response using "https://www.gmibank.com/api/tp-countries"
    And user saves the countries to correspondent files
    Then user validates the countries

  Scenario Outline:  delete countries
    Given user deletes a country using endpoint "<endPoint>" and its extension "<id>"
    Examples: multiple countries
      | endPoint                                 | id     |
      | https://www.gmibank.com/api/tp-countries | /59638 |

  @wip
  Scenario: US_021 read all countries
    Given get all countries
    And verify that all countries data
    Then verify that data of country id 60778

  Scenario: US_025 creation of a new country
    Given send a new country with generated name
    Then verify that new country is created


  Scenario: US_026 update a random selected country
    Given update a random selected country with a new generated name
    Then verify that country information is update
