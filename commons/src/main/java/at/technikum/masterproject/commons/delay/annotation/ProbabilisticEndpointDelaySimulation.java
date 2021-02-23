package at.technikum.masterproject.commons.delay.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ProbabilisticEndpointDelaySimulation {

  float probability() default 0;

  int delayInMs() default 0;

}
