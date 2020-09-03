package at.technikum.masterproject.model.delay;

import java.util.Random;

public class ProbabilisticDelay implements Delay {

  private final float probability;
  private final int potentialDelayInMs;
  private boolean isDelaying = false;

  public ProbabilisticDelay(float probability, int potentialDelayInMs) {
    this.probability = probability;
    this.potentialDelayInMs = potentialDelayInMs;
  }

  private final Random random = new Random();

  private void shuffle() {
    isDelaying = (random.nextInt() * 100) > probability;
  }

  @Override
  public int getDelayInMs() {
    shuffle();
    return isDelaying ? potentialDelayInMs : 0;
  }
}
