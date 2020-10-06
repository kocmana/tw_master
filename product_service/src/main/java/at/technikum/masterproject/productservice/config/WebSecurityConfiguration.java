package at.technikum.masterproject.productservice.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final BasicAuthEntryPoint authenticationEntryPoint;
  private final SecurityWhitelistProperties securityWhitelistProperties;

  @Autowired
  public WebSecurityConfiguration(BasicAuthEntryPoint authenticationEntryPoint,
      SecurityWhitelistProperties securityWhitelistProperties) {
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.securityWhitelistProperties = securityWhitelistProperties;
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("admin").password(passwordEncoder().encode("adminPassword"))
        .authorities("ROLE_USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/actuator/**")
        .permitAll()
        .antMatchers(generateConfigurationWhitelist())
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .authenticationEntryPoint(authenticationEntryPoint);
  }

  private String[] generateConfigurationWhitelist() {
    List<String> whitelist = securityWhitelistProperties.getWhitelist();
    return whitelist.toArray(new String[0]);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
