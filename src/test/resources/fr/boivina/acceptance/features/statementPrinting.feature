Feature: In order to check my operations
         As a bank client
         I want to see the history (operation, date, amount, balance)  of my operations

  Scenario: The client display a single operations history
    Given a new bank account
    And the client makes a deposit of 10.0
    When he displays the operations history
    Then the following statement is displayed with date close to now
"""
|TYPE        |DATE        |AMOUNT      |BALANCE     |
|DEPOSIT     |2017-10-24  |10.00       |0.00        |
"""

  Scenario: The client display a multiple operations history
    Given a new bank account
    And the client makes a deposit of 10.0
    And the client makes a withdrawal of 3.5
    When he displays the operations history
    Then the following statement is displayed with date close to now
"""
|TYPE        |DATE        |AMOUNT      |BALANCE     |
|DEPOSIT     |2017-10-24  |10.00       |0.00        |
|WITHDRAWAL  |2017-10-24  |3.50        |10.00       |
"""
