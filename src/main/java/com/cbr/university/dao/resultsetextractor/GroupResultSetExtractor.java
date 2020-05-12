package com.cbr.university.dao.resultsetextractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.cbr.university.model.Group;
import com.cbr.university.model.Student;

public class GroupResultSetExtractor implements ResultSetExtractor<List<Group>> {

    @Override
    public List<Group> extractData(ResultSet rs) throws SQLException {
        Map<Group, List<Student>> groupaAndStudentsMap = new HashMap<>();
        Group group = new Group();

        while (rs.next()) {
            group.setId(rs.getInt("groups_group_id"));
            group.setName(rs.getString("group_name"));
            groupaAndStudentsMap.putIfAbsent(group, new ArrayList<>());

            int studentId = rs.getInt("student_id");
            if (studentId > 0) {
                Student student = new Student();
                student.setId(studentId);
                student.setFirstName(rs.getString("student_first_name"));
                student.setLastName(rs.getString("student_last_name"));
                student.setGroup(group);
                groupaAndStudentsMap.get(group).add(student);
            }
        }
        return extractMapToList(groupaAndStudentsMap);
    }

    private List<Group> extractMapToList(Map<Group, List<Student>> groupaAndStudentsMap) {
        List<Group> groups = new ArrayList<>();

        for (Map.Entry<Group, List<Student>> entry : groupaAndStudentsMap.entrySet()) {
            Group group = entry.getKey();
            group.setStudents(entry.getValue());
            groups.add(group);
        }
        return groups;
    }
}
