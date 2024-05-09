package com.cbr.university;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                                //.requestMatchers("/", "/index", "/error").permitAll()
                                //.requestMatchers("/viewing/**").hasRole("STUDENT")
                                //.requestMatchers("/viewing/**").permitAll()
                                //.requestMatchers("/editing/**").hasRole("EDITOR")
                                //.requestMatchers("/editing/**").permitAll()
                                //.requestMatchers("/rest/**").hasRole("REST")
                                // .requestMatchers("/error").permitAll() // Иначе будет плавающая ложная ошибка 401 при вызове permitAll-методов
                                //.requestMatchers("/swagger-ui/**", "/v3/**").permitAll()
                                .anyRequest().authenticated()

//                        .formLogin().loginPage("/login").permitAll().and()
//                        .logout().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web
//                .debug(false) // Нужно ли?
//                .ignoring()
//                .requestMatchers("/webjars/**", "/images/**", "/css/**", "/assets/**", "/favicon.ico");
//    }

    @Bean
    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("student").password("1234").roles("STUDENT")
//                .username("editor").password("4321").roles("EDITOR")
//                .username("user").password("password2").roles("USER")
//                .username("rest").password("1122").roles("REST")
//                .build();

        UserDetails userStudent = User.withDefaultPasswordEncoder()
                .username("student").password("1234").roles("STUDENT").build();
        UserDetails userEditor = User.withDefaultPasswordEncoder()
                .username("editor").password("4321").roles("EDITOR").build();
        UserDetails userRest = User.withDefaultPasswordEncoder()
                .username("rest").password("1122").roles("REST").build();

        return new InMemoryUserDetailsManager(userStudent, userEditor, userRest);
    }

//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth
//                .inMemoryAuthentication()
//                .withUser("student").password(encoder.encode("1234")).roles("STUDENT").and()
//                .withUser("editor").password(encoder.encode("4321")).roles("EDITOR").and()
//                .withUser("rest").password(encoder.encode("1122")).roles("REST");
//    }

//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/index", "/error").permitAll()
//                .antMatchers("/viewing/**").hasRole("STUDENT")
//                .antMatchers("/editing/**").hasRole("EDITOR")
//                .antMatchers("/rest/**").hasRole("REST")
//                .anyRequest().denyAll().and()
//
//                .formLogin().loginPage("/login").permitAll().and()
//                .logout().permitAll().and()
//                .httpBasic();
//    }
}
