package com.ascending.blair.config;

import com.ascending.blair.extend.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");
    }

    protected void configure(HttpSecurity http) throws Exception{
//        http.csrf().disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin();

        // add authantication
        http.csrf().disable().authorizeRequests().antMatchers("/api/users/login", "/api/user/login", "/api/user/signup", "/api/users/signup").permitAll()
                .and()
                    .authorizeRequests().antMatchers("/api/**").hasAnyRole("REGISTERED_USER", "ADMIN")
                .and()
                    .formLogin()
                .and()
                    .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
    }
}
