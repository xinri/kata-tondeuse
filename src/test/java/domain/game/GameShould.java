package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;

public class GameShould {

  @Test
  public void return_an_empty_list_when_create_an_empty_game() {
    // given
    var game = new Game(5, 5);

    // when
    game.execute();

    // then
    assertThat(game.getTondeuseList()).isEmpty();
  }

  @Test
  public void should_one_tondeuse_at_0_0_N_when_it_is_created_at_this_position_without_movement() {
    // given
    var game = new Game(5, 5);
    game.addTondeuse(0, 0, "N", "");

    // when
    game.execute();

    // then
    assertThat(game.getTondeuseList()).hasSize(1);
    assertThat(game.getTondeuseList()).extracting("x", "y", "direction")
        .contains(Tuple.tuple(0, 0, "N"));
  }
}
