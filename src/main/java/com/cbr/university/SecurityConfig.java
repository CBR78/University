package com.cbr.university;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                                //.anyRequest().authenticated()
                                .anyRequest().permitAll()

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
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails userStudent = User
                .withUsername("student")
                .password(passwordEncoder.encode("1234")).roles("STUDENT").build();
        UserDetails userEditor = User
                .withUsername("editor")
                .password(passwordEncoder.encode("4321")).roles("EDITOR").build();
        UserDetails userRest = User
                .withUsername("rest")
                .password(passwordEncoder.encode("1122")).roles("REST").build();

        return new InMemoryUserDetailsManager(userStudent, userEditor, userRest);
    }

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
