package com.cbr.university.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.cbr.university.dao.impl.GroupDaoImpl;
import com.cbr.university.model.Group;
import com.cbr.university.model.Student;

public class StudentRowMapper implements RowMapper<Student> {
    @Autowired
    GroupDaoImpl groupDaoImpl;

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("student_id"));
        student.setFirstName(resultSet.getString("student_first_name"));
        student.setLastName(resultSet.getString("student_last_name"));

        Group group = new Group();
        group.setId(resultSet.getInt("groups_group_id"));
        group.setName(resultSet.getString("group_name"));

        student.setGroup(group);
        return student;
    }
}
