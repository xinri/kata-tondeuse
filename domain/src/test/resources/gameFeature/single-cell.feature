Feature: move into one cell
  Scenario: move 2 tondeuse into 1 cell
    Given create a field with the upper right conner is (0, 0)
    And add a tondeuse at (0, 0) oriented N with orders GAGAGAGAA
    And add a tondeuse at (0, 0) oriented E with orders AADAADADDA
    When active the tondeuse on the field
    Then I find the tondeuse 1 at (0, 0) oriented N
    And I find the tondeuse 2 at (0, 0) oriented E