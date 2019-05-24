package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class GameShould {

  @Test
  public void should_create_game_without_tondeuses() {
    // given
    var game = new Game(5, 5);

    // when
    game.execute();

    // then
    assertThat(game.getTondeuseList()).isEmpty();
  }
}
