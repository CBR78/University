package com.cbr.university.dao.impl;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.dao.resultsetextractor.GroupResultSetExtractor;
import com.cbr.university.model.Group;

@Repository
public class GroupDaoImpl implements BaseDao<Group> {
    private static final String SQL_INSERT = "INSERT INTO groups (group_name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE groups SET group_name = ? WHERE group_id = ?";
    private static final String SQL_DELETE = "DELETE FROM groups WHERE group_id = ?";
    private static final String SQL_GET_ALL = "SELECT groups.group_id AS groups_group_id, groups.group_name, students.student_id, students.student_first_name, students.student_last_name, students.group_id AS students_group_id FROM groups LEFT JOIN students ON groups.group_id = students.group_id";
    private static final String SQL_GET_BY_ID = "SELECT groups.group_id AS groups_group_id, groups.group_name, students.student_id, students.student_first_name, students.student_last_name, students.group_id AS students_group_id FROM groups LEFT JOIN students ON groups.group_id = students.group_id WHERE group_id = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GroupDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Group group) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
            ps.setString(1, group.getName());
            return ps;
        }, keyHolder);

        int groupId = keyHolder.getKey().intValue();
        group.setId(groupId);
    }

    @Override
    public void update(Group group) {
        jdbcTemplate.update(SQL_UPDATE, group.getName(), group.getId());
    }

    @Override
    public void delete(Group group) {
        jdbcTemplate.update(SQL_DELETE, group.getId());
    }

    @Override
    public List<Group> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new GroupResultSetExtractor());
    }

    @Override
    public Group getById(int id) {
        List<Group> groups = jdbcTemplate.query(SQL_GET_BY_ID, new GroupResultSetExtractor());
        return groups.get(0);
    }
}
