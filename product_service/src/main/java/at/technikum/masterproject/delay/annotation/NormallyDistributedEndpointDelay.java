package at.technikum.masterproject.delay.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NormallyDistributedEndpointDelay {

  int mean() default 0;
  int standardDeviation() default 0;

}
