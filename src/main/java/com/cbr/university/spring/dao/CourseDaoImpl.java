package com.cbr.university.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cbr.university.model.Course;
import com.cbr.university.model.CourseMapper;

@Component
public class CourseDaoImpl implements BaseDao<Course> {

    private static final String SQL_INSERT = "INSERT INTO courses(course_name) VALUES(?)";
    private static final String SQL_UPDATE = "UPDATE courses SET course_name = ? WHERE course_id = ?";
    private static final String SQL_DELETE = "DELETE FROM courses WHERE course_id = ?";
    private static final String SQL_GET_ALL = "SELECT * FROM courses";
    private static final String SQL_GET_BY_ID = "SELECT * FROM courses WHERE course_id = ?";
    JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean create(Course course) {
        return jdbcTemplate.update(SQL_INSERT, course.getName()) > 0;
    }

    public boolean update(Course course) {
        return jdbcTemplate.update(SQL_UPDATE, course.getName(), course.getId()) > 0;
    }

    public boolean delete(Course course) {
        return jdbcTemplate.update(SQL_DELETE, course.getId()) > 0;
    }

    public List<Course> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new CourseMapper());
    }

    public Course getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new CourseMapper());
    }

}
