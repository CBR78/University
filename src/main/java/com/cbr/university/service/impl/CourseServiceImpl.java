package com.cbr.university.service.impl;

import com.cbr.university.model.Course;
import com.cbr.university.repository.CourseRepository;
import com.cbr.university.service.BaseService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class CourseServiceImpl implements BaseService<Course> {

    private final CourseRepository courseRepository;
    private final EntityManager entityManager;

    public CourseServiceImpl(CourseRepository courseRepository, EntityManager entityManager) {
        this.courseRepository = courseRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void delete(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public List<Course> getAll() {
        entityManager.clear();
        return courseRepository.findAll();
    }

    @Override
    public Course getById(int id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public boolean existsById(int id) {
        return courseRepository.existsById(id);
    }
}
