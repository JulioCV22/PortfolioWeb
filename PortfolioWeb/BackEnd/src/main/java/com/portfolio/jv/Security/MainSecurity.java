
package com.portfolio.jv.Security;

import com.portfolio.jv.Security.Service.UserDetailsImpl;
import com.portfolio.jv.Security.jwt.JwtEntryPoint;
import com.portfolio.jv.Security.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity extends WebSecurityConfigurerAdapter{
    @Autowired
    UserDetailsImpl serDetailsImpl;
    @Autowired
    JwtEntryPoint JwtTokenPoint;
    
    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
  protected void configure(HttpSecurity http) throws Exception {
      http.cors().and().csrf().disable()
              .authorizeHttpRequests()
              .antMatchers("**")
              .permitAll()
              .anyRequest()
              .authenticated
              .and()
              .exceptionHandling()
              .authenticationEntryPoint(JwtEntryPoint)
              .and()
              .sessionMangement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  }
  
     public AuthenticationManager authenticationManager() throws Exception {
      return super.authenticationManager();
  }
  
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
  }

  
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(serDetailsImpl).passwordEncoder(passwordEncoder());
  }
}