package at.technikum.masterproject.model.delay;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FixedDelay implements Delay {

  private int delayInMs;

  @Override
  public int getDelayInMs() {
    return delayInMs;
  }
}
