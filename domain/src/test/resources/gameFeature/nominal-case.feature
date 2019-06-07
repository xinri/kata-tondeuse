Feature: nominal case
  Scenario: Create a game and run it
    Given create a field with the upper right conner is (5, 5)
    And add a tondeuse at (1, 2) oriented N with orders GAGAGAGAA
    And add a tondeuse at (3, 3) oriented E with orders AADAADADDA
    When active the tondeuse on the field
    Then I find the tondeuse 1 at (1, 3) oriented N
    And I find the tondeuse 2 at (5, 1) oriented E