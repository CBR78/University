package com.cbr.university.dao.impl;

import java.util.List;

import javax.sql.DataSource;

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
    private static final String SQL_GET_ALL = "SELECT * FROM courses";
    private static final String SQL_GET_BY_ID = "SELECT * FROM courses WHERE course_id = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Course course) {
        jdbcTemplate.update(SQL_INSERT, course.getName());
    }

    public void update(Course course) {
        jdbcTemplate.update(SQL_UPDATE, course.getName(), course.getId());
    }

    public void delete(Course course) {
        jdbcTemplate.update(SQL_DELETE, course.getId());
    }

    public List<Course> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new CourseRowMapper());
    }

    public Course getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new CourseRowMapper());
    }

}
