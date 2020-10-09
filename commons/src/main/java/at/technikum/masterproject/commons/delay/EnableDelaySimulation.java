package at.technikum.masterproject.commons.delay;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import at.technikum.masterproject.commons.delay.config.DelayConfig;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Retention(value = RUNTIME)
@Target(value = TYPE)
@Documented
@Import(value = DelayConfig.class)
public @interface EnableDelaySimulation {

}
