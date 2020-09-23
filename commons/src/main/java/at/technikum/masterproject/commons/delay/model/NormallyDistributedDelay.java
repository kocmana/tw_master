package at.technikum.masterproject.commons.delay.model;

import java.util.Random;

public class NormallyDistributedDelay implements Delay {

  private final int mean;
  private final int standardDeviation;
  private final Random random = new Random();
  private int delayInMs;

  public NormallyDistributedDelay(int mean, int standardDeviation) {
    this.mean = mean;
    this.standardDeviation = standardDeviation;
    shuffle();
  }

  private void shuffle() {
    int randomDelay = (int) Math.round(random.nextGaussian() * standardDeviation + mean);
    delayInMs = Math.max(0, randomDelay);
  }

  @Override
  public int getDelayInMs() {
    return delayInMs;
  }

  @Override
  public void delay() {
    Delay.super.delay();
    shuffle();
  }
}
