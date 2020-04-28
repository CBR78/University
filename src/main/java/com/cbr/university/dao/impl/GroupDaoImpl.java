package com.cbr.university.dao.impl;

import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.dao.SpringConfig;
import com.cbr.university.dao.resultsetextractor.GroupResultSetExtractor;
import com.cbr.university.model.Group;
import com.cbr.university.model.Student;

@Repository
public class GroupDaoImpl implements BaseDao<Group> {
    private static final String SQL_INSERT = "INSERT INTO groups (group_name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE groups SET group_name = ? WHERE group_id = ?";
    private static final String SQL_DELETE = "DELETE FROM groups WHERE group_id = ?";
    private static final String SQL_GET_ALL = "SELECT * FROM groups LEFT JOIN students ON groups.group_id = students.group_id";
    private static final String SQL_GET_BY_ID = "SELECT * FROM groups LEFT JOIN students ON groups.group_id = students.group_id WHERE group_id = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GroupDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Group group) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
            ps.setString(1, group.getName());
            return ps;
        }, keyHolder);

        int groupId = keyHolder.getKey().intValue();
        group.setId(groupId);
        setStudentsGroup(group.getStudents(), groupId);
    }

    public void update(Group group) {
        jdbcTemplate.update(SQL_UPDATE, group.getName(), group.getId());
        setStudentsGroup(group.getStudents(), group.getId());
    }

    public void delete(Group group) {
        jdbcTemplate.update(SQL_DELETE, group.getId());
        setStudentsGroup(group.getStudents(), 0);
    }

    public List<Group> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new GroupResultSetExtractor());
    }

    public Group getById(int id) {
        List<Group> groups = jdbcTemplate.query(SQL_GET_BY_ID, new GroupResultSetExtractor()); 
        return groups.get(0);
    }

    private void setStudentsGroup(List<Student> students, int groupId) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class);
        StudentDaoImpl studentDaoImpl = context.getBean(StudentDaoImpl.class);

        for (Student student : students) {
            student.setGroupId(groupId);
            studentDaoImpl.update(student);
        }

        context.close();
    }
}
