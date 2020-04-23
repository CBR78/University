package com.cbr.university.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cbr.university.model.Group;
import com.cbr.university.model.GroupMapper;

@Component
public class GroupDaoImpl implements BaseDao<Group> {

    private static final String SQL_INSERT = "INSERT INTO groups (group_name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE groups SET group_name = ? WHERE group_id = ?";
    private static final String SQL_DELETE = "DELETE FROM groups WHERE group_id = ?";
    private static final String SQL_GET_ALL = "SELECT * FROM groups";
    private static final String SQL_GET_BY_ID = "SELECT * FROM groups WHERE group_id = ?";
    JdbcTemplate jdbcTemplate;

    @Autowired
    public GroupDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean create(Group group) {
        return jdbcTemplate.update(SQL_INSERT, group.getName()) > 0;
    }

    public boolean update(Group group) {
        return jdbcTemplate.update(SQL_UPDATE, group.getName(), group.getId()) > 0;
    }

    public boolean delete(Group group) {
        return jdbcTemplate.update(SQL_DELETE, group.getId()) > 0;
    }

    public List<Group> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new GroupMapper());
    }

    public Group getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new GroupMapper());
    }
}
