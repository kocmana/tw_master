package at.technikum.master_project.aspect;

import java.lang.reflect.Method;
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

  @Around("@annotation(at.technikum.master_project.aspect.DelayedEndpointFixed)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    DelayedEndpointFixed annotation = extractAnnotation(joinPoint);
    int delayInMs = annotation.delayInMs();

    log.info("Sleeping for {}ms...", delayInMs);

    Thread.sleep(delayInMs);
    return joinPoint.proceed();
  }

  private DelayedEndpointFixed extractAnnotation(ProceedingJoinPoint joinPoint) {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    Method method = methodSignature.getMethod();
    return method.getAnnotation(DelayedEndpointFixed.class);
  }

}
