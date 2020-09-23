package at.technikum.masterproject.commons.requestlogging;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import at.technikum.masterproject.commons.requestlogging.config.LoggingConfig;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Retention(value = RUNTIME)
@Target(value = TYPE)
@Documented
@Import(value = LoggingConfig.class)
public @interface EnableRequestLogging {

}
