package com.cbr.university.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cbr.university.model.Course;

public class CourseMapper implements RowMapper<Course> {

    public Course mapRow(ResultSet resultSet, int i) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getInt("course_id"));
        course.setName(resultSet.getString("course_name"));
        return course;
    }
}
