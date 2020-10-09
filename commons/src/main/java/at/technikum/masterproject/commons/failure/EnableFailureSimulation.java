package at.technikum.masterproject.commons.failure;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import at.technikum.masterproject.commons.failure.config.FailureConfig;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Retention(value = RUNTIME)
@Target(value = TYPE)
@Documented
@Import(value = FailureConfig.class)
public @interface EnableFailureSimulation {

}
