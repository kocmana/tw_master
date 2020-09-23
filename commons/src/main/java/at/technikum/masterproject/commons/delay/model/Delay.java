package at.technikum.masterproject.commons.delay.model;

import static java.lang.Thread.sleep;

public interface Delay {

  int getDelayInMs();

  default void delay() {
    try {
      sleep(getDelayInMs());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
