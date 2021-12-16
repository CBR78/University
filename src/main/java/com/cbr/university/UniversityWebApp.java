package com.cbr.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

//@EnableJpaRepositories("com.cbr.university.repository") - theoretically, speed up the start
//@EntityScan("com.cbr.university.model")
@SpringBootApplication
public class UniversityWebApp extends SpringBootServletInitializer implements WebMvcConfigurer {

    private static final String INDEX = "index";

    public static void main(String[] args) {
        SpringApplication.run(UniversityWebApp.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName(INDEX);
        registry.addViewController("/index").setViewName(INDEX);
        registry.addViewController("/login").setViewName(INDEX);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }
}
