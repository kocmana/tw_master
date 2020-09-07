package at.technikum.masterproject.config;

import static java.util.Collections.singletonList;

import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfiguration {

  @Bean
  public Docket productInformationApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .paths(PathSelectors.any())
        .build()
        .pathMapping("/")
        .genericModelSubstitutes(ResponseEntity.class)
        .useDefaultResponseMessages(false)
        .apiInfo(apiInfo())
        .securitySchemes(singletonList(basicAuth()))
        .securityContexts(singletonList(securityContext()))
        .enableUrlTemplating(true)
        .tags(new Tag("ProductInformationService", "All apis relating to product information"));
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Product Information API")
        .description("Documentation for the product information simulation API.")
        .version("1.0")
        .build();
  }

  private BasicAuth basicAuth() {
    return new BasicAuth("BasicAuth", Collections.emptyList());
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(basicAuthReference())
        .forPaths(PathSelectors.any())
        .build();
  }

  List<SecurityReference> basicAuthReference() {
    return Collections.singletonList(new SecurityReference("BasicAuth", new AuthorizationScope[0]));
  }

}
