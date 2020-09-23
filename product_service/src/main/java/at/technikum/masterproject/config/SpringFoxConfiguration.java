package at.technikum.masterproject.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfiguration {

  /* Setup of Dockets currently not working due to blocking bug reported here
   https://github.com/springfox/springfox/issues/2235
   */

}
