Feature: In order to save money
         As a bank client
         I want to make a deposit in my account

  Scenario: The client make a positive deposit in his account
    Given a new bank account
    When the client makes a deposit of 10.0
    Then the account balance must be 10.0
