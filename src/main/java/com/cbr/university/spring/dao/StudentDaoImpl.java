package com.cbr.university.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cbr.university.model.Student;
import com.cbr.university.model.StudentMapper;

@Component
public class StudentDaoImpl implements BaseDao<Student> {
    private static final String SQL_INSERT = "INSERT INTO students (student_first_name, student_last_name, group_id) VALUES (?,?,?)";
    private static final String SQL_UPDATE = "UPDATE students SET student_first_name = ?, student_last_name = ?, group_id = ? WHERE student_id = ?";
    private static final String SQL_DELETE = "DELETE FROM students WHERE student_id = ?";
    private static final String SQL_GET_ALL = "SELECT * FROM students";
    private static final String SQL_GET_BY_ID = "SELECT * FROM students WHERE student_id = ?";
    JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean create(Student student) {
        return jdbcTemplate.update(SQL_INSERT, student.getFirstName(), student.getLastName(),
                student.getGroupId()) > 0;
    }

    public boolean update(Student student) {
        return jdbcTemplate.update(SQL_UPDATE, student.getFirstName(), student.getLastName(),
                student.getGroupId(), student.getId()) > 0;
    }

    public boolean delete(Student student) {
        return jdbcTemplate.update(SQL_DELETE, student.getId()) > 0;
    }

    public List<Student> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new StudentMapper());
    }

    public Student getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new StudentMapper());
    }
}
