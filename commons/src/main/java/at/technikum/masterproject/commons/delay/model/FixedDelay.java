package at.technikum.masterproject.commons.delay.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FixedDelay implements Delay {

  private final int delayInMs;

  @Override
  public int getDelayInMs() {
    return delayInMs;
  }

}
