package at.technikum.masterproject.commons.failure.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ProbabilisticFailureSimulation {

  float probability() default 0;

  int errorCode() default 500;
}
