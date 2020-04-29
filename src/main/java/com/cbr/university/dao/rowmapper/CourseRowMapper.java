package com.cbr.university.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cbr.university.model.Course;

public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet resultSet, int i) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getInt("course_id"));
        course.setName(resultSet.getString("course_name"));
        return course;
    }
}
