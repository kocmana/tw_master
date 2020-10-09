package at.technikum.masterproject.commons.failure.aspect;

import static at.technikum.masterproject.commons.util.AnnotationUtils.extractAnnotation;

import at.technikum.masterproject.commons.failure.annotation.ProbabilisticFailureSimulation;
import at.technikum.masterproject.commons.failure.model.ProbabilisticFailure;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class FailureAspect {

  @Around("@annotation(at.technikum.masterproject.commons.failure.annotation.ProbabilisticFailureSimulation)")
  public Object failWithProbability(ProceedingJoinPoint joinPoint) throws Throwable {
    ProbabilisticFailureSimulation annotation = extractAnnotation(joinPoint, ProbabilisticFailureSimulation.class);
    ProbabilisticFailure probabilisticFailure = new ProbabilisticFailure(annotation.probability(), annotation.errorCode());

    if (probabilisticFailure.shouldCallFall()) {
      log.warn("Call failed with error code {} due to simulated probabilistic error.",
          probabilisticFailure.getHttpStatus());
      return ResponseEntity.status(probabilisticFailure.getHttpStatus()).build();
    }

    return joinPoint.proceed();
  }

}
