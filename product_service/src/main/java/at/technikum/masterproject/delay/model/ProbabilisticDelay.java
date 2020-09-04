package at.technikum.masterproject.delay.model;

import java.util.Random;

public class ProbabilisticDelay implements Delay {

  private final float probability;
  private final int potentialDelayInMs;
  private boolean isDelaying = false;

  private final Random random = new Random();

  public ProbabilisticDelay(float probability, int potentialDelayInMs) {
    this.probability = probability;
    this.potentialDelayInMs = potentialDelayInMs;
    shuffle();
  }

  private void shuffle() {
    isDelaying = (random.nextInt() * 100) > probability;
  }

  @Override
  public int getDelayInMs() {
    return isDelaying ? potentialDelayInMs : 0;
  }

  @Override
  public void delay() {
    Delay.super.delay();
    shuffle();
  }

}
