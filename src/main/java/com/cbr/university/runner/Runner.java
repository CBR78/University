package com.cbr.university.runner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cbr.university.model.Course;
import com.cbr.university.spring.config.AppConfig;
import com.cbr.university.spring.dao.CourseDaoImpl;

public class Runner {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                AppConfig.class);
        CourseDaoImpl courseDAOImpl = context.getBean(CourseDaoImpl.class);
        
        System.out.println("courseDAOImpl.create");
        Course course = new Course();
        course.setName("Mathematics");
        courseDAOImpl.create(course);
        
        System.out.println("courseDAOImpl.getAll");
        for (Course listСourse : courseDAOImpl.getAll()) {
            System.out.println(listСourse);
        }
        
        System.out.println("------------------------------------------");
        System.out.println("courseDAOImpl.getById(1)");
        Course courseById = courseDAOImpl.getById(1);
        System.out.println(courseById);
        
        System.out.println("------------------------------------------");
        courseById.setName("Sopromat");
        System.out.println("courseDAOImpl.update - Sopromat");
        courseDAOImpl.update(courseById);
        
        System.out.println("courseDAOImpl.getAll");
        for (Course listСourse : courseDAOImpl.getAll()) {
            System.out.println(listСourse);
        }
        
        System.out.println("------------------------------------------");
        System.out.println("courseDAOImpl.delete - Sopromat");
        courseDAOImpl.delete(courseById);
        
        System.out.println("courseDAOImpl.getAll");
        for (Course listСourse : courseDAOImpl.getAll()) {
            System.out.println(listСourse);
        }
        
        context.close();
    }
}
