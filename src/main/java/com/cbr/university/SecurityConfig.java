package com.cbr.university;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .inMemoryAuthentication()
                .withUser("student").password(encoder.encode("1234")).roles("STUDENT").and()
                .withUser("editor").password(encoder.encode("4321")).roles("EDITOR").and()
                .withUser("rest").password(encoder.encode("1122")).roles("REST");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index", "/error").permitAll()
                .antMatchers("/viewing/**").hasRole("STUDENT")
                .antMatchers("/editing/**").hasRole("EDITOR")
                .antMatchers("/rest/**").hasRole("REST")
                .anyRequest().denyAll().and()

                .formLogin().loginPage("/login").permitAll().and()
                .logout().permitAll().and()
                .httpBasic();
    }
}
