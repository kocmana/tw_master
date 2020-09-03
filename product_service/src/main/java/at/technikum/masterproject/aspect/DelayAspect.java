package at.technikum.masterproject.aspect;

import at.technikum.masterproject.model.delay.ProbabilisticDelay;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DelayAspect {

  @Around("@annotation(at.technikum.masterproject.aspect.FixedEndpointDelay)")
  public Object delayExecutionWithFixedDuration(ProceedingJoinPoint joinPoint) throws Throwable {
    FixedEndpointDelay annotation = extractAnnotation(joinPoint, FixedEndpointDelay.class);
    int delayInMs = annotation.delayInMs();

    log.info("Sleeping for {}ms...", delayInMs);

    Thread.sleep(delayInMs);
    return joinPoint.proceed();
  }

  //TODO
//  @Around("@annotation(at.technikum.masterproject.aspect.NormallyDistributedEndpointDelay)")
//  public Object delayExecutionWithNormallyDistributedDuration(ProceedingJoinPoint joinPoint) throws Throwable {
//    NormallyDistributedEndpointDelay annotation = extractAnnotation(joinPoint, NormallyDistributedEndpointDelay.class);
//    int mean = annotation.mean();
//    int standardDeviation = annotation.standardDeviation();
//
//    log.info("Sleeping for {}ms...", delayInMs);
//
//    Thread.sleep(delayInMs);
//    return joinPoint.proceed();
//  }

  @Around("@annotation(at.technikum.masterproject.aspect.ProbabilisticEndpointDelay)")
  public Object delayExecutionWithProbability(ProceedingJoinPoint joinPoint) throws Throwable {
    ProbabilisticEndpointDelay annotation = extractAnnotation(joinPoint, ProbabilisticEndpointDelay.class);
    int delayInMs = new ProbabilisticDelay(annotation.probability(), annotation.duration()).getDelayInMs();

    log.info("Sleeping for {}ms...", delayInMs);

    Thread.sleep(delayInMs);
    return joinPoint.proceed();
  }

  private <T extends Annotation> T extractAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationType) {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    Method method = methodSignature.getMethod();

    Supplier<IllegalStateException> generateIllegalStateException =
        () -> new IllegalStateException("Could not extract annotation, while looking for %s.");

    return Optional.ofNullable(method.getAnnotation(annotationType))
        .filter(annotationType::isInstance)
        .map(annotationType::cast)
        .orElseThrow(generateIllegalStateException);
  }

}
