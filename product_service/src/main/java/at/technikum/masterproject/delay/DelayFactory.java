package at.technikum.masterproject.delay;

import at.technikum.masterproject.delay.annotation.FixedEndpointDelay;
import at.technikum.masterproject.delay.annotation.NormallyDistributedEndpointDelay;
import at.technikum.masterproject.delay.annotation.ProbabilisticEndpointDelay;
import at.technikum.masterproject.delay.model.FixedDelay;
import at.technikum.masterproject.delay.model.NormallyDistributedDelay;
import at.technikum.masterproject.delay.model.ProbabilisticDelay;
import org.springframework.stereotype.Component;

@Component
public class DelayFactory {

  public FixedDelay createDelayFromAnnotation(FixedEndpointDelay aspect) {
    return new FixedDelay(aspect.delayInMs());
  }

  public NormallyDistributedDelay createDelayFromAnnotation(NormallyDistributedEndpointDelay aspect) {
    return new NormallyDistributedDelay(aspect.mean(), aspect.standardDeviation());
  }

  public ProbabilisticDelay createDelayFromAnnotation(ProbabilisticEndpointDelay aspect) {
    return new ProbabilisticDelay(aspect.probability(), aspect.duration());
  }

}
