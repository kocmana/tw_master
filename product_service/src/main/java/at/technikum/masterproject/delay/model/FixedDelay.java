package at.technikum.masterproject.delay.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FixedDelay implements Delay {

  private final int delayInMs;

  @Override
  public int getDelayInMs() {
    return delayInMs;
  }

}
