package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.impl.CourseDaoImpl;
import com.cbr.university.model.Course;
import com.cbr.university.service.BaseService;

@Service
public class CourseServiceImpl implements BaseService<Course> {

    private CourseDaoImpl courseDaoImpl;

    @Autowired
    public CourseServiceImpl(CourseDaoImpl courseDaoImpl) {
        this.courseDaoImpl = courseDaoImpl;
    }

    @Override
    public void create(Course course) {
        courseDaoImpl.create(course);
    }

    @Override
    public void update(Course course) {
        courseDaoImpl.update(course);
    }

    @Override
    public void delete(Course course) {
        courseDaoImpl.delete(course);
    }

    @Override
    public List<Course> getAll() {
        return courseDaoImpl.getAll();
    }

    @Override
    public Course getById(int id) {
        return courseDaoImpl.getById(id);
    }
}
