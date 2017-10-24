Feature: In order to retrieve some or all of my savings
         As a bank client
         I want to make a withdrawal from my account

  Scenario: The client make a full withdrawal in his account
    Given a new bank account
    And the client makes a deposit of 10.0
    When the client makes a withdrawal of 10.0
    And the account balance must be 0.0

  Scenario: The client make a partial withdrawal in his account
    Given a new bank account
    And the client makes a deposit of 10.0
    When the client makes a withdrawal of 7.0
    And the account balance must be 3.0

  Scenario: The client make a withdrawal greater than his account balance
    Given a new bank account
    And the client makes a deposit of 10.0
    When the client makes a withdrawal of 11.0
    And the account balance must be -1.0
