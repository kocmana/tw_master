package at.technikum.masterproject.model.delay;

import java.util.Random;

public class NormallyDistributedDelay implements Delay {

  private final int mean;
  private final int standardDeviation;
  private int currentDelay;

  private final Random random = new Random();

  public NormallyDistributedDelay(int mean, int standardDeviation) {
    this.mean = mean;
    this.standardDeviation = standardDeviation;
  }

  private void shuffle() {
    int randomDelay = (int) Math.round(random.nextGaussian() * standardDeviation + mean);
    currentDelay = Math.max(0, randomDelay);
  }

  @Override
  public int getDelayInMs() {
    shuffle();
    return currentDelay;
  }
}
