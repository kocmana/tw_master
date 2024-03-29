package at.technikum.masterproject.customerservice.config;

import at.technikum.masterproject.customerservice.interceptor.ApiKeyAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final AuthenticationManager authenticationManager;
  private final ApiKeyProperties apiKeyProperties;

  @Autowired
  public WebSecurityConfiguration(ApiKeyProperties apiKeyProperties) {
    this.apiKeyProperties = apiKeyProperties;
    authenticationManager = generateAuthenticationManager();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    ApiKeyAuthenticationFilter filter = new ApiKeyAuthenticationFilter(apiKeyProperties.getHeader());
    filter.setAuthenticationManager(authenticationManager);

    httpSecurity
        .antMatcher("/**")
        .csrf().disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilter(filter)
        .authorizeRequests()
        .anyRequest().authenticated();
  }

  AuthenticationManager generateAuthenticationManager() {
    return authentication -> {
      String principal = (String) authentication.getPrincipal();
      if (!apiKeyProperties.getValues().contains(principal)) {
        throw new BadCredentialsException("Invalid Credentials.");
      }
      authentication.setAuthenticated(true);
      return authentication;
    };
  }

}
