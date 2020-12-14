@All
Feature:  System should allow any user to register with valid credentials validating the success message

  Background:
    Given user navigates to "Register" page under account menu icon

  @US01TC01
  Scenario Outline: US_001 TC_001 There should be a valid SSN respecting the "-" where necessary, it should be 9 digits long
    When user types a "ssn" "<SSN Number>" and press TAB
    Then verify that no message is displayed

    Examples: Test data for SSN
      | SSN Number  |
      | 092-01-0813 |
      | 123-00-1345 |


  @US01TC02
  Scenario Outline: US_001 TC_002 There should be a valid name that contains chars
    Given user types a "firstname" "<First Name>" and press TAB
    Then verify that no message is displayed
    Examples:
      | First Name |
      | Betul      |

  @US01TC03
  Scenario Outline: US_001 TC_003 There should be a valid last name that contains chars
    Given user types a "lastname" "<Last Name>" and press TAB
    Then verify that no message is displayed
    Examples:
      | Last Name |
      | Ulu       |

  @US01TC04
  Scenario Outline: US_001 TC_004 We can provide chars and digits to describe a valid address along with zip code
    Given user types a "address" "<Address>" and press TAB
    Then verify that no message is displayed
    Examples:
      | Address                             |
      | Eydibaba mah cikmaz sok no:11 50674 |

  @US01TC05
  Scenario Outline: US_001 TC_005 User should provide 10 digit-long mobilephone number as a required field respecting the "-"
    Given user types a "mobilephonenumber" "<Mobile Phone Number>" and press TAB
    Then verify that no message is displayed
    Examples:
      | Mobile Phone Number |
      | 092-101-0812        |

  @US01TC06
  Scenario Outline: US_001 TC_006 user can use both of chars and digits at the same time in any length
    Given user types a "username" "<Username>" and press TAB
    Then verify that no message is displayed
    Examples:
      | Username |
      | BetulUlu |
      | Betul123 |


  @US01TC07
  Scenario Outline: US_001 TC_007 We should provide a valid email format that contains "@", ".com" extensions in order
    Given user types a "email" "<Email>" and press TAB
    Then verify that no message is displayed
    Examples:
      | Email              |
      | Betululu@gmail.com |
      | Betul@gmail.tr     |

  @US01TC08
  Scenario Outline: US_001 TC_007 Password confirmation entry should be same with password
    Given user types a "newpassword" "<Password>" and press TAB
    And user types a "newpasswordconfirmation" "<Password>" and press TAB
    Then verify that no message is displayed
    Examples:
      | Password  |
      | 1234      |
      | *Asndjf34 |

#alttakinin assertiniu duzelt

  @US02TC01
  Scenario Outline: US_002 TC_001 Verify that user cannot register with an ssn number which does not match pattern such as ###-##-####

    When user types a "ssn" "<SSN Number>" and press TAB
    Then Verify that this error message "Your SSN is invalid" is displayed

    Examples:
      | SSN Number    |
      | 092-0134-0813 |
      | 12-0-15       |
      | 123*00*1345   |
      | -1235678-99   |


  @US02TC04
  Scenario Outline: US_002 TC_004 Verify that Last Name cannot include any digit
    Given user types a "Lastname" "<Last Name>" and press TAB
    Then  Verify that this error message "Your Last name is invalid" is displayed
    Examples:
      | Last Name |
      | Ulu123    |

  @US02TC05
  Scenario Outline: US_002 TC_005 Verify that first name cannot include any digit
    Given user types a "firstname" "<First Name>" and press TAB
    Then  Verify that this error message "Your first name is invalid" is displayed
    Examples:
      | First Name   |
      | Betul123 Ulu |

  @US02TC06
  Scenario Outline: US_002 TC_006 Verify that user cannot register with an ssn number which does not match pattern such as ###-###-####
    Given user types a "mobilephonenumber" "<Mobile Phone Number>" and press TAB
    Then  Verify that error message "Your mobile phone number is invalid" is displayed under "mobilephonenumber" textbox
    Examples:
      | Mobile Phone Number |
      | 092/101/0812        |
      | 2-11-02             |
      | 0922-1017-0812      |

  @US01TC07
  Scenario Outline: US_002 TC_0011 email id cannot be created without "@" sign and ".com" extension
    Given user types a "email" "<Email>" and press TAB
    Then  Verify that this error message "This field is invalid" is displayed
    Examples:
      | Email              |
      | Betululu#gmail.net |
      | Betul@gmail.       |
      | .tr@emregmail      |
      | @emre.comgmail     |
      | emre@.gmail        |

  @US01TC08
  Scenario: US_002 TC_0012 ssn cannot be left blank
    Given user types click and leaves blank "ssn" textbox
    Then  Verify that this error message "Your SSN is required" is displayed

  @US01TC09
  Scenario: US_002 TC_0013 email cannot be left blank
    Given user types click and leaves blank "email" textbox
    Then  Verify that this error message "Your email is required." is displayed