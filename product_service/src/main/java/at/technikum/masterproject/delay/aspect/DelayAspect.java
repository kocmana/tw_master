package at.technikum.masterproject.delay.aspect;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

import at.technikum.masterproject.delay.DelayFactory;
import at.technikum.masterproject.delay.annotation.FixedEndpointDelay;
import at.technikum.masterproject.delay.annotation.NormallyDistributedEndpointDelay;
import at.technikum.masterproject.delay.annotation.ProbabilisticEndpointDelay;
import at.technikum.masterproject.delay.model.Delay;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DelayAspect {

  DelayFactory delayFactory;

  @Autowired
  public DelayAspect(DelayFactory delayFactory) {
    this.delayFactory = delayFactory;
  }

  @Around("@annotation(at.technikum.masterproject.delay.annotation.FixedEndpointDelay)")
  public Object delayExecutionWithFixedDuration(ProceedingJoinPoint joinPoint) throws Throwable {
    FixedEndpointDelay annotation = extractAnnotation(joinPoint, FixedEndpointDelay.class);
    Delay delay = delayFactory.createDelayFromAnnotation(annotation);

    delayResponse(delay);

    return joinPoint.proceed();
  }

  @Around("@annotation(at.technikum.masterproject.delay.annotation.NormallyDistributedEndpointDelay)")
  public Object delayExecutionWithNormallyDistributedDuration(ProceedingJoinPoint joinPoint)
      throws Throwable {
    NormallyDistributedEndpointDelay annotation = extractAnnotation(joinPoint, NormallyDistributedEndpointDelay.class);
    Delay delay = delayFactory.createDelayFromAnnotation(annotation);

    delayResponse(delay);

    return joinPoint.proceed();
  }

  @Around("@annotation(at.technikum.masterproject.delay.annotation.ProbabilisticEndpointDelay)")
  public Object delayExecutionWithProbability(ProceedingJoinPoint joinPoint) throws Throwable {
    ProbabilisticEndpointDelay annotation = extractAnnotation(joinPoint, ProbabilisticEndpointDelay.class);
    Delay delay = delayFactory.createDelayFromAnnotation(annotation);

    delayResponse(delay);

    return joinPoint.proceed();
  }

  private <T extends Annotation> T extractAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationType) {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    Method method = methodSignature.getMethod();

    return findAnnotation(method, annotationType);
  }

  private void delayResponse(Delay delay) {
    log.info("Delaying API response for {}ms...", delay.getDelayInMs());
    delay.delay();
  }

}
