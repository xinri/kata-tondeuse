package cucumberstep;

import static org.assertj.core.api.Assertions.assertThat;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import field.Field;
import field.valuetype.LimitField;

public class GameSteps {

  private Field field;

  @Given("create a field with the upper right conner is \\({int}, {int})")
  public void createAFieldWithTheUpperRightConnerIs(int maxX, int maxY) {
    this.field = new Field(new LimitField(maxX, maxX));
  }

  @And("add a tondeuse at \\({int}, {int}) oriented {word} with orders {word}")
  public void addATondeuseAtOrientedNWithOrdersGAGAGAGAA(int x, int y, String oriented,
      String orders) {
    this.field.addTondeuse(x, y, oriented, orders);
  }

  @When("active the tondeuse on the field")
  public void theGameIsLaunch() {
    this.field.execute();
  }

  @Then("I find the tondeuse {int} at \\({int}, {int}) oriented {word}")
  public void iFindTheTondeuseAtOrientedN(int indice, int x, int y, String oriented) {
    assertThat(this.field.getTondeuseList().get(indice - 1))
        .hasFieldOrPropertyWithValue("position.x", x)
        .hasFieldOrPropertyWithValue("position.y", y)
        .hasFieldOrPropertyWithValue("direction", oriented);
  }
}
