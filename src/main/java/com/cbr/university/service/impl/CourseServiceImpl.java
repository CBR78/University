package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.model.Course;
import com.cbr.university.repository.CourseRepositroy;
import com.cbr.university.service.BaseService;

@Service
public class CourseServiceImpl implements BaseService<Course> {

    private CourseRepositroy courseDao;

    @Autowired
    public CourseServiceImpl(CourseRepositroy courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void create(Course course) {
        courseDao.save(course);
    }

    @Override
    public void update(Course course) {
        courseDao.save(course);
    }

    @Override
    public void delete(Course course) {
        courseDao.delete(course);
    }

    @Override
    public List<Course> getAll() {
        return (List<Course>) courseDao.findAll();
    }

    @Override
    public Course getById(int id) {
        return courseDao.findById(id).get();
    }
}
