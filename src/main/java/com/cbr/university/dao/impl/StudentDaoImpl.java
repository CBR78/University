package com.cbr.university.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.dao.rowmapper.StudentRowMapper;
import com.cbr.university.model.Student;

@Repository
public class StudentDaoImpl implements BaseDao<Student> {
    private static final String SQL_INSERT = "INSERT INTO students (student_first_name, student_last_name, group_id) VALUES (?,?,?)";
    private static final String SQL_UPDATE = "UPDATE students SET student_first_name = ?, student_last_name = ?, group_id = ? WHERE student_id = ?";
    private static final String SQL_DELETE = "DELETE FROM students WHERE student_id = ?";
    private static final String SQL_GET_ALL = "SELECT groups.group_id AS groups_group_id, groups.group_name, students.student_id, students.student_first_name, students.student_last_name, students.group_id AS students_group_id FROM students LEFT JOIN groups ON students.group_id = groups.group_id";
    private static final String SQL_GET_BY_ID = "SELECT groups.group_id AS groups_group_id, groups.group_name, students.student_id, students.student_first_name, students.student_last_name, students.group_id AS students_group_id FROM students LEFT JOIN groups ON students.group_id = groups.group_id WHERE student_id = ?";
    private static final String SQL_GET_BY_GROUP = "SELECT student_id, student_first_name, student_last_name, group_id FROM students WHERE group_id = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Student student) {
        jdbcTemplate.update(SQL_INSERT, student.getFirstName(), student.getLastName(),
                student.getGroup().getId());
    }

    @Override
    public void update(Student student) {
        jdbcTemplate.update(SQL_UPDATE, student.getFirstName(), student.getLastName(),
                student.getGroup().getId(), student.getId());
    }

    @Override
    public void delete(Student student) {
        jdbcTemplate.update(SQL_DELETE, student.getId());
    }

    @Override
    public List<Student> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new StudentRowMapper());
    }

    @Override
    public Student getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new StudentRowMapper());
    }

    public List<Student> getByGroup(int groupId) {
        return jdbcTemplate.query(SQL_GET_BY_GROUP, new Object[] { groupId }, new StudentRowMapper());
    }
}
