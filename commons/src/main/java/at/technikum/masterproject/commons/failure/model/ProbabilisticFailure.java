package at.technikum.masterproject.commons.failure.model;

import java.util.Random;
import lombok.Value;

@Value
public class ProbabilisticFailure {

  float probability;
  int httpStatus;
  Random random = new Random();

  public boolean shouldCallFall() {
    return (random.nextInt() * 100) > probability;
  }

}
