package at.technikum.masterproject.commons.delay;

import at.technikum.masterproject.commons.delay.annotation.FixedEndpointDelay;
import at.technikum.masterproject.commons.delay.annotation.NormallyDistributedEndpointDelay;
import at.technikum.masterproject.commons.delay.annotation.ProbabilisticEndpointDelay;
import at.technikum.masterproject.commons.delay.model.FixedDelay;
import at.technikum.masterproject.commons.delay.model.NormallyDistributedDelay;
import at.technikum.masterproject.commons.delay.model.ProbabilisticDelay;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DelayFactory {

  public static FixedDelay createDelayFromAnnotation(FixedEndpointDelay aspect) {
    return new FixedDelay(aspect.delayInMs());
  }

  public static NormallyDistributedDelay createDelayFromAnnotation(NormallyDistributedEndpointDelay aspect) {
    return new NormallyDistributedDelay(aspect.mean(), aspect.standardDeviation());
  }

  public static ProbabilisticDelay createDelayFromAnnotation(ProbabilisticEndpointDelay aspect) {
    return new ProbabilisticDelay(aspect.probability(), aspect.duration());
  }

}
