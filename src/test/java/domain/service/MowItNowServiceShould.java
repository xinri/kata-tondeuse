package domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import infrastructure.adapter.GameFileAdapter;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class MowItNowServiceShould {

  @Test
  public void call_the_file_adapter_when_service_call_the_file_rule() throws IOException {
    // given
    var fileAdapter = mock(GameFileAdapter.class);
    given(fileAdapter.executeGame("FileName")).willReturn("result");

    var mowItNowService = new MowItNowService(fileAdapter);

    // when
    var result = mowItNowService.launchGameFromFile("FileName");

    // then
    verify(fileAdapter).executeGame("FileName");
    assertThat(result).isEqualTo("result");
  }
}