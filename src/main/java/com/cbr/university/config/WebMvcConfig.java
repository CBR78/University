package com.cbr.university.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.cbr.university.model.Course;
import com.cbr.university.model.Group;
import com.cbr.university.model.Room;
import com.cbr.university.model.ScheduleLine;
import com.cbr.university.model.Student;
import com.cbr.university.model.Teacher;

@Configuration
@ComponentScan("com.cbr.university")
@EnableWebMvc
@EnableTransactionManagement
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ApplicationContext applicationContext;

    private SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    private SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        registry.viewResolver(viewResolver);
    }

    @Bean
    public DataSource getDataSource() throws NamingException {
        JndiTemplate jndiTemplate = new JndiTemplate();
        DataSource dataSource = (DataSource) jndiTemplate.lookup("java:comp/env/jdbc/postgres");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() throws NamingException {
        Properties properties = new Properties();
        properties.put("hibernate.showSql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(Course.class, Group.class, Room.class, Teacher.class,
                Student.class, ScheduleLine.class);
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() throws NamingException {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
