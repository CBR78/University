package com.cbr.university.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cbr.university.model.Course;

@Configuration
@ComponentScan("com.cbr.university")
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public DataSource getDataSource() {
        JndiTemplate jndiTemplate = new JndiTemplate();
        DataSource dataSource = (DataSource) new Object();
        try {
            dataSource = (DataSource) jndiTemplate.lookup("java:comp/env/jdbc/postgres");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        Properties properties = new Properties();
        properties.put("hibernate.showSql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(Course.class);  //----------------------------------
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
