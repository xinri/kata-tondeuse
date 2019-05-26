package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.game.valuetype.LimitFieldValueType;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;

public class GameShould {

  @Test
  public void return_an_empty_list_when_create_an_empty_game() {
    // given
    var game = new Game(new LimitFieldValueType(5, 5));

    // when
    game.execute();

    // then
    assertThat(game.getTondeuseList()).isEmpty();
  }

  @Test
  public void should_one_tondeuse_at_0_0_N_when_it_is_created_at_this_position_without_movement() {
    // given
    var game = new Game(new LimitFieldValueType(5, 5));
    game.addTondeuse(0, 0, "N", "");

    // when
    game.execute();

    // then
    assertThat(game.getTondeuseList()).hasSize(1);
    assertThat(game.getTondeuseList()).extracting("x", "y", "direction")
        .contains(Tuple.tuple(0, 0, "N"));
  }

  @Test
  public void should_one_tondeuse_at_1_3_N_when_it_executes_GAGAGAGAA_on_1_2_N() {
    // given
    var game = new Game(new LimitFieldValueType(5, 5));
    game.addTondeuse(1, 2, "N", "GAGAGAGAA");

    // when
    game.execute();

    // then
    assertThat(game.getTondeuseList()).hasSize(1);
    assertThat(game.getTondeuseList()).extracting("x", "y", "direction")
        .contains(Tuple.tuple(1, 3, "N"));
  }

  @Test
  public void should_execute_nominal_case1() {
    // given
    var game = new Game(new LimitFieldValueType(5, 5));
    game.addTondeuse(1, 2, "N", "GAGAGAGAA");
    game.addTondeuse(3, 3, "E", "AADAADADDA");

    // when
    game.execute();

    // then
    assertThat(game.getTondeuseList()).hasSize(2);
    assertThat(game.getTondeuseList()).extracting("x", "y", "direction")
        .contains(Tuple.tuple(1, 3, "N"));
    assertThat(game.getTondeuseList()).extracting("x", "y", "direction")
        .contains(Tuple.tuple(5, 1, "E"));
  }
}
