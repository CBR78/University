package com.cbr.university.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.dao.rowmapper.CourseRowMapper;
import com.cbr.university.model.Course;

@Repository
public class CourseDaoImpl implements BaseDao<Course> {
    private static final String SQL_INSERT = "INSERT INTO courses(course_name) VALUES(?)";
    private static final String SQL_UPDATE = "UPDATE courses SET course_name = ? WHERE course_id = ?";
    private static final String SQL_DELETE = "DELETE FROM courses WHERE course_id = ?";
    private static final String SQL_GET_ALL = "SELECT course_id, course_name FROM courses";
    private static final String SQL_GET_BY_ID = "SELECT course_id, course_name FROM courses WHERE course_id = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Course course) {
        jdbcTemplate.update(SQL_INSERT, course.getName());
    }

    @Override
    public void update(Course course) {
        jdbcTemplate.update(SQL_UPDATE, course.getName(), course.getId());
    }

    @Override
    public void delete(Course course) {
        jdbcTemplate.update(SQL_DELETE, course.getId());
    }

    @Override
    public List<Course> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new CourseRowMapper());
    }

    @Override
    public Course getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new CourseRowMapper());
    }
}
