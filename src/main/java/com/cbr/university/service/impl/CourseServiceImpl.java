package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.model.Course;
import com.cbr.university.service.BaseService;

@Service
public class CourseServiceImpl implements BaseService<Course> {

    private BaseDao<Course> courseDao;

    @Autowired
    public CourseServiceImpl(BaseDao<Course> courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void create(Course course) {
        courseDao.create(course);
    }

    @Override
    public void update(Course course) {
        courseDao.update(course);
    }

    @Override
    public void delete(Course course) {
        courseDao.delete(course);
    }

    @Override
    public List<Course> getAll() {
        return courseDao.getAll();
    }

    @Override
    public Course getById(int id) {
        return courseDao.getById(id);
    }
}
