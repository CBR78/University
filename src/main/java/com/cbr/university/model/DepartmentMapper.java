package com.cbr.university.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DepartmentMapper implements RowMapper<Department> {

    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("department_id"));
        department.setName(resultSet.getString("department_name"));
        return department;
    }
}
