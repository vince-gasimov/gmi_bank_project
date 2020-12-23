Feature:Us_27 System should allow to delete states using api end point

  @us271
  Scenario:US_27_TC01 User can just delete each state 1 by 1
    Given user deletes the state of a given id
    Then verifies the state he deleted
