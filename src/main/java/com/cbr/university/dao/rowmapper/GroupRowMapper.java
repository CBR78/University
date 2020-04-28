package com.cbr.university.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import com.cbr.university.dao.SpringConfig;
import com.cbr.university.dao.impl.StudentDaoImpl;
import com.cbr.university.model.Group;
import com.cbr.university.model.Student;

public class GroupRowMapper implements RowMapper<Group> {

    public Group mapRow(ResultSet resultSet, int i) throws SQLException {
        Group group = new Group();
        group.setId(resultSet.getInt("group_id"));
        group.setName(resultSet.getString("group_name"));
        group.setStudents(getStudentsGroup(group));
        return group;
    }

    private List<Student> getStudentsGroup(Group group) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class);
        StudentDaoImpl studentDaoImpl = context.getBean(StudentDaoImpl.class);

        int groupId = group.getId();
        List<Student> studentsGroup = studentDaoImpl.getByGroup(groupId);

        context.close();
        return studentsGroup;
    }
}
