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
        Map<Integer, Group> groupsMap = new HashMap<>();
        Group group = null;

        while (rs.next()) {
            int groupId = rs.getInt("groups_group_id");

            if (!groupsMap.containsKey(groupId)) {
                group = new Group();
                group.setStudents(new ArrayList<Student>());
                groupsMap.put(groupId, group);
            }

            group = groupsMap.get(groupId);
            group.setId(groupId);
            group.setName(rs.getString("group_name"));

            int studentId = rs.getInt("student_id");
            if (studentId > 0) {
                Student student = new Student();
                student.setId(studentId);
                student.setFirstName(rs.getString("student_first_name"));
                student.setLastName(rs.getString("student_last_name"));
                student.setGroup(group);
                group.getStudents().add(student);
            }
        }
        return extractMapToList(groupsMap);
    }

    private List<Group> extractMapToList(Map<Integer, Group> groupsMap) {
        List<Group> groups = new ArrayList<>();
        for (Map.Entry<Integer, Group> entry : groupsMap.entrySet()) {
            Group group = entry.getValue();
            groups.add(group);
        }
        return groups;
    }
}
