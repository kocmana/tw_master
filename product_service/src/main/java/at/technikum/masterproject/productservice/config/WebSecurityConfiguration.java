package at.technikum.masterproject.productservice.config;

import java.security.SecureRandom;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final String ADMIN_ROLE = "ADMIN";
  private static final String USER_ROLE = "USER";

  private final BasicAuthEntryPoint authenticationEntryPoint;
  private final SecurityWhitelistProperties securityWhitelistProperties;
  private final DataSource dataSource;

  @Autowired
  public WebSecurityConfiguration(BasicAuthEntryPoint authenticationEntryPoint,
      SecurityWhitelistProperties securityWhitelistProperties, DataSource dataSource) {
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.securityWhitelistProperties = securityWhitelistProperties;
    this.dataSource = dataSource;
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(HttpMethod.DELETE, "/**").hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.POST, "/product/**").hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.PATCH, "/product/**").hasRole(ADMIN_ROLE)
        .antMatchers("/review/**").hasAnyRole(ADMIN_ROLE, USER_ROLE)
        .antMatchers(HttpMethod.GET, "/**").hasAnyRole(ADMIN_ROLE, USER_ROLE)

        .antMatchers("/actuator/**").permitAll()
        .antMatchers(generateConfigurationWhitelist()).permitAll()

        .anyRequest()
        .authenticated()

        .and()
        .httpBasic()
        .authenticationEntryPoint(authenticationEntryPoint);

    http.csrf().disable()
        .headers().frameOptions().disable();
  }

  private String[] generateConfigurationWhitelist() {
    List<String> whitelist = securityWhitelistProperties.getWhitelist();
    return whitelist.toArray(new String[0]);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10, new SecureRandom());
  }

}
