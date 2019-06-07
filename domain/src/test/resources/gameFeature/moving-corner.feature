Feature: moving on the corner
  Scenario: Create a field and move the tondeuse on the upper part
    Given create a field with the upper right conner is (5, 5)
    And add a tondeuse at (2, 0) oriented S with orders GGAAAAAAGAAAAAA
    And add a tondeuse at (2, 0) oriented W with orders DDAAAAAAGAAAAA
    When active the tondeuse on the field
    Then I find the tondeuse 1 at (0, 5) oriented W
    And I find the tondeuse 2 at (5, 5) oriented N

  Scenario:  Create a field and move the tondeuse on the lower part
    Given create a field with the upper right conner is (5, 5)
    And add a tondeuse at (2, 5) oriented S with orders AAAAADAAAAG
    And add a tondeuse at (2, 5) oriented W with orders GAGADAGADAGADAGADAG
    When active the tondeuse on the field
    Then I find the tondeuse 1 at (0, 0) oriented S
    And I find the tondeuse 2 at (5, 0) oriented E

  Scenario: create a field with 4 tondeuse each going to each corner
    Given create a field with the upper right conner is (5, 5)
    And add a tondeuse at (2, 0) oriented S with orders GGAAAAAAGAAAAAA
    And add a tondeuse at (2, 0) oriented W with orders DDAAAAAAGAAAAA
    And add a tondeuse at (2, 5) oriented S with orders AAAAADAAAAG
    And add a tondeuse at (2, 5) oriented W with orders GAGADAGADAGADAGADAG
    When active the tondeuse on the field
    Then I find the tondeuse 1 at (0, 5) oriented W
    And I find the tondeuse 2 at (5, 5) oriented N
    And I find the tondeuse 3 at (0, 0) oriented S
    And I find the tondeuse 4 at (5, 0) oriented E