package at.technikum.masterproject.commons.delay.aspect;

import static at.technikum.masterproject.commons.delay.DelayFactory.createDelayFromAnnotation;
import static at.technikum.masterproject.commons.util.AnnotationUtils.extractAnnotation;

import at.technikum.masterproject.commons.delay.annotation.FixedEndpointDelaySimulation;
import at.technikum.masterproject.commons.delay.annotation.NormallyDistributedEndpointDelaySimulation;
import at.technikum.masterproject.commons.delay.annotation.ProbabilisticEndpointDelaySimulation;
import at.technikum.masterproject.commons.delay.config.DelayProperties;
import at.technikum.masterproject.commons.delay.model.Delay;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnProperty(prefix = "service.delay", name = "enable-endpoint-delays",
    havingValue = "true")
@Slf4j
public class DelayAspect {

  private final DelayProperties delayProperties;

  @Autowired
  public DelayAspect(DelayProperties delayProperties) {
    this.delayProperties = delayProperties;
    log.warn("Delay annotation active.");
  }

  @Around("@annotation(at.technikum.masterproject.commons.delay.annotation.FixedEndpointDelaySimulation)")
  public Object delayExecutionWithFixedDuration(ProceedingJoinPoint joinPoint) throws Throwable {
    FixedEndpointDelaySimulation annotation = extractAnnotation(joinPoint, FixedEndpointDelaySimulation.class);
    Delay delay = createDelayFromAnnotation(annotation);

    delayResponse(delay);

    return joinPoint.proceed();
  }

  @Around("@annotation(at.technikum.masterproject.commons.delay.annotation.NormallyDistributedEndpointDelaySimulation)")
  public Object delayExecutionWithNormallyDistributedDuration(ProceedingJoinPoint joinPoint)
      throws Throwable {
    NormallyDistributedEndpointDelaySimulation annotation = extractAnnotation(joinPoint,
        NormallyDistributedEndpointDelaySimulation.class);
    Delay delay = createDelayFromAnnotation(annotation);

    delayResponse(delay);

    return joinPoint.proceed();
  }

  @Around("@annotation(at.technikum.masterproject.commons.delay.annotation.ProbabilisticEndpointDelaySimulation)")
  public Object delayExecutionWithProbability(ProceedingJoinPoint joinPoint) throws Throwable {
    ProbabilisticEndpointDelaySimulation annotation = extractAnnotation(joinPoint,
        ProbabilisticEndpointDelaySimulation.class);
    Delay delay = createDelayFromAnnotation(annotation);

    delayResponse(delay);

    return joinPoint.proceed();
  }

  private void delayResponse(Delay delay) {
    if (delayProperties.isLogDelays()) {
      log.info("Delaying API response for {}ms...", delay.getDelayInMs());
    }
    delay.delay();
  }

}
