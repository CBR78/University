package com.cbr.university.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cbr.university.model.Course;
import com.cbr.university.model.Department;
import com.cbr.university.model.DepartmentMapper;
import com.cbr.university.model.Group;
import com.cbr.university.model.Teacher;

@Component
public class DepartmentDaoImpl implements BaseDao<Department> {

    private static final String SQL_INSERT = "INSERT INTO departments (department_name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE departments SET department_name = ? WHERE department_id = ?";
    private static final String SQL_DELETE = "DELETE FROM departments WHERE department_id = ?";
    private static final String SQL_GET_ALL = "SELECT * FROM departments";
    private static final String SQL_GET_BY_ID = "SELECT * FROM departments WHERE department_id = ?";
    JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean create(Department department) {
        

        for (Group group : department.getGroups()) {

        }

        for (Course course : department.getCourses()) {

        }
        
        for (Teacher teacher : department.getTeachers()) {

        }

        return jdbcTemplate.update(SQL_INSERT, department.getName()) > 0;
    }

    public boolean update(Department department) {
        return jdbcTemplate.update(SQL_UPDATE, department.getName(), department.getId()) > 0;
    }

    public boolean delete(Department department) {
        return jdbcTemplate.update(SQL_DELETE, department.getId()) > 0;
    }

    public List<Department> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new DepartmentMapper());
    }

    public Department getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new DepartmentMapper());
    }
}
