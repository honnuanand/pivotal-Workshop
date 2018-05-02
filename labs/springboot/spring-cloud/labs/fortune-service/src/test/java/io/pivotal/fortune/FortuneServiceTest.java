package io.pivotal.fortune;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class FortuneServiceTest {

  private FortuneService fortuneService;

  @Before
  public void setup() {
    fortuneService = new FortuneService();
  }

  @Test
  public void shouldReturnAFortune() {
    assertThat(fortuneService.getFortune()).isNotNull();
  }

  @Test
  public void shouldReturnSomeDistinctFortunes() {
    Set<String> fortunes = new HashSet<>();
    for (int i=0; i<1000; i++) {
      fortunes.add(fortuneService.getFortune());
    }
    assertThat(fortunes.size()).isGreaterThanOrEqualTo(4);
  }
}
