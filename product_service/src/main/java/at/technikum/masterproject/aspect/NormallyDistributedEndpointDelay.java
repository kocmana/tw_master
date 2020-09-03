package at.technikum.masterproject.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NormallyDistributedEndpointDelay {

  int mean() default 0;
  int standardDeviation() default 0;

}
