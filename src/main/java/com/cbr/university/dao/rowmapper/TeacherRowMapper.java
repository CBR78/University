package com.cbr.university.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.cbr.university.dao.impl.CourseDaoImpl;
import com.cbr.university.model.Course;
import com.cbr.university.model.Teacher;

public class TeacherRowMapper implements RowMapper<Teacher> {
    @Autowired
    CourseDaoImpl courseDaoImpl;
    
    @Override
    public Teacher mapRow(ResultSet resultSet, int i) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getInt("teacher_id"));
        teacher.setFirstName(resultSet.getString("teacher_first_name"));
        teacher.setLastName(resultSet.getString("teacher_last_name"));
    
        Course course = new Course();
        course.setId(resultSet.getInt("courses_course_id"));
        course.setName(resultSet.getString("course_name"));
        
        teacher.setCourse(course);
        return teacher;
    }
}
