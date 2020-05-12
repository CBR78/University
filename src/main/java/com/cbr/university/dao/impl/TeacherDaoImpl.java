package com.cbr.university.dao.impl;

import java.util.List;

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
    private static final String SQL_GET_ALL = "SELECT teacher_id, teacher_first_name, teacher_last_name, teachers.course_id AS teachers_course_id, course_name, courses.course_id AS courses_course_id FROM teachers LEFT JOIN courses ON teachers.course_id = courses.course_id";
    private static final String SQL_GET_BY_ID = "SELECT teacher_id, teacher_first_name, teacher_last_name, teachers.course_id AS teachers_course_id, course_name, courses.course_id AS courses_course_id FROM teachers LEFT JOIN courses ON teachers.course_id = courses.course_id WHERE teacher_id = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TeacherDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Teacher teacher) {
        jdbcTemplate.update(SQL_INSERT, teacher.getFirstName(), teacher.getLastName(),
                teacher.getCourse().getId());
    }

    @Override
    public void update(Teacher teacher) {
        jdbcTemplate.update(SQL_UPDATE, teacher.getFirstName(), teacher.getLastName(),
                teacher.getCourse().getId(), teacher.getId());
    }

    @Override
    public void delete(Teacher teacher) {
        jdbcTemplate.update(SQL_DELETE, teacher.getId());
    }

    @Override
    public List<Teacher> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new TeacherRowMapper());
    }

    @Override
    public Teacher getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new TeacherRowMapper());
    }
}
