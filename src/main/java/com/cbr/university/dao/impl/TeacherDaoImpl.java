package com.cbr.university.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.dao.rowmapper.TeacherRowMapper;
import com.cbr.university.model.Teacher;

@Repository
public class TeacherDaoImpl implements BaseDao<Teacher> {
    private static final String SQL_INSERT = "INSERT INTO teachers (teacher_first_name, teacher_last_name, course_id) VALUES (?,?,?)";
    private static final String SQL_UPDATE = "UPDATE teachers SET teacher_first_name = ?, teacher_last_name = ?, course_id = ? WHERE teacher_id = ?";
    private static final String SQL_DELETE = "DELETE FROM teachers WHERE teacher_id = ?";
    private static final String SQL_GET_ALL = "SELECT * FROM teachers";
    private static final String SQL_GET_BY_ID = "SELECT * FROM teachers WHERE teacher_id = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TeacherDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Teacher teacher) {
        jdbcTemplate.update(SQL_INSERT, teacher.getFirstName(), teacher.getLastName(),
                teacher.getCourseId());
    }

    public void update(Teacher teacher) {
        jdbcTemplate.update(SQL_UPDATE, teacher.getFirstName(), teacher.getLastName(),
                teacher.getCourseId(), teacher.getId());
    }

    public void delete(Teacher teacher) {
        jdbcTemplate.update(SQL_DELETE, teacher.getId());
    }

    public List<Teacher> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new TeacherRowMapper());
    }

    public Teacher getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new TeacherRowMapper());
    }
}
