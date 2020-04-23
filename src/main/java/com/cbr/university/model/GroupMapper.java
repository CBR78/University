package com.cbr.university.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GroupMapper implements RowMapper<Group> {
    
    public Group mapRow(ResultSet resultSet, int i) throws SQLException {
        Group group = new Group();
        group.setId(resultSet.getInt("group_id"));
        group.setName(resultSet.getString("group_name"));
        return group;
    }
}
