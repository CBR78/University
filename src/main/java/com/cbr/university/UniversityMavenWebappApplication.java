package com.cbr.university;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.cbr.university.model.Course;
import com.cbr.university.model.Group;
import com.cbr.university.model.Room;
import com.cbr.university.model.ScheduleLine;
import com.cbr.university.model.Student;
import com.cbr.university.model.Teacher;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
public class UniversityMavenWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityMavenWebappApplication.class, args);
    }

    @Bean
    public DataSource getDataSource() throws NamingException {
        JndiTemplate jndiTemplate = new JndiTemplate();
        return (DataSource) jndiTemplate.lookup("java:comp/env/jdbc/postgres");
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
