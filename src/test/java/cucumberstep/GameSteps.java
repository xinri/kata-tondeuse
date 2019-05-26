package cucumberstep;

import static org.assertj.core.api.Assertions.assertThat;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.game.Game;
import domain.game.valuetype.LimitFieldValueType;

public class GameSteps {

  private Game game;

  @Given("create a field with the upper right conner is \\({int}, {int})")
  public void createAFieldWithTheUpperRightConnerIs(int maxX, int maxY) {
    this.game = new Game(new LimitFieldValueType(maxX, maxX));
  }

  @And("add a tondeuse at \\({int}, {int}) oriented {word} with orders {word}")
  public void addATondeuseAtOrientedNWithOrdersGAGAGAGAA(int x, int y, String oriented,
      String orders) {
    this.game.addTondeuse(x, y, oriented, orders);
  }

  @When("the game is launch")
  public void theGameIsLaunch() {
    this.game.execute();
  }

  @Then("I find the tondeuse {int} at \\({int}, {int}) oriented {word}")
  public void iFindTheTondeuseAtOrientedN(int indice, int x, int y, String oriented) {
    assertThat(this.game.getTondeuseList().get(indice - 1))
        .hasFieldOrPropertyWithValue("x", x)
        .hasFieldOrPropertyWithValue("y", y)
        .hasFieldOrPropertyWithValue("direction", oriented);
  }
}
