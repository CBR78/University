package com.cbr.university.dao.impl;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.dao.rowmapper.ScheduleLineRowMapper;
import com.cbr.university.model.ScheduleLine;

@Repository
public class ScheduleLineDaoImpl implements BaseDao<ScheduleLine> {
    private static final String SQL_INSERT = "INSERT INTO schedule_lines (schedule_line_date, lesson_pair, group_id, teacher_id, course_id, room_id) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE schedule_lines SET schedule_line_date = ?, lesson_pair = ?, group_id = ?, teacher_id = ?, course_id = ?, room_id = ? WHERE schedule_line_id = ?";
    private static final String SQL_DELETE = "DELETE FROM schedule_lines WHERE schedule_line_id = ?";
    private static final String SQL_GET_ALL = "SELECT schedule_line_id, schedule_line_date, lesson_pair, schedule_lines.group_id AS schedule_lines_group_id, groups.group_id AS groups_group_id, groups.group_name AS groups_group_name, schedule_lines.teacher_id AS schedule_lines_teacher_id, teachers.teacher_id AS teachers_teacher_id, teachers.teacher_first_name AS teachers_teacher_first_name, teachers.teacher_last_name AS teachers_teacher_last_name, schedule_lines.course_id AS schedule_lines_course_id, courses.course_id AS courses_course_id, courses.course_name AS courses_course_name, schedule_lines.room_id AS schedule_lines_room_id, rooms.room_id AS rooms_room_id, rooms.room_name AS rooms_room_name FROM schedule_lines LEFT JOIN groups ON schedule_lines.group_id = groups.group_id LEFT JOIN teachers ON schedule_lines.group_id = teachers.teacher_id LEFT JOIN courses ON schedule_lines.group_id = courses.course_id LEFT JOIN rooms ON schedule_lines.group_id = rooms.room_id";
    private static final String SQL_GET_BY_ID = "SELECT schedule_line_id, schedule_line_date, lesson_pair, schedule_lines.group_id AS schedule_lines_group_id, groups.group_id AS groups_group_id, groups.group_name AS groups_group_name, schedule_lines.teacher_id AS schedule_lines_teacher_id, teachers.teacher_id AS teachers_teacher_id, teachers.teacher_first_name AS teachers_teacher_first_name, teachers.teacher_last_name AS teachers_teacher_last_name, schedule_lines.course_id AS schedule_lines_course_id, courses.course_id AS courses_course_id, courses.course_name AS courses_course_name, schedule_lines.room_id AS schedule_lines_room_id, rooms.room_id AS rooms_room_id, rooms.room_name AS rooms_room_name FROM schedule_lines LEFT JOIN groups ON schedule_lines.group_id = groups.group_id LEFT JOIN teachers ON schedule_lines.group_id = teachers.teacher_id LEFT JOIN courses ON schedule_lines.group_id = courses.course_id LEFT JOIN rooms ON schedule_lines.group_id = rooms.room_id WHERE schedule_line_id = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleLineDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(ScheduleLine scheduleLine) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
            ps.setObject(1, scheduleLine.getDate());
            ps.setString(2, scheduleLine.getLessonPair().getPairStartTime());
            ps.setInt(3, scheduleLine.getGroup().getId());
            ps.setInt(4, scheduleLine.getTeacher().getId());
            ps.setInt(5, scheduleLine.getCourse().getId());
            ps.setInt(6, scheduleLine.getRoom().getId());
            return ps;
        }, keyHolder);

        int scheduleLineId = keyHolder.getKey().intValue();
        scheduleLine.setId(scheduleLineId);
    }

    @Override
    public void update(ScheduleLine scheduleLine) {
        jdbcTemplate.update(SQL_UPDATE, scheduleLine.getDate(),
                scheduleLine.getLessonPair().getPairStartTime(), scheduleLine.getGroup().getId(),
                scheduleLine.getTeacher().getId(), scheduleLine.getCourse().getId(),
                scheduleLine.getRoom().getId(), scheduleLine.getId());
    }

    @Override
    public void delete(ScheduleLine scheduleLine) {
        jdbcTemplate.update(SQL_DELETE, scheduleLine.getId());
    }

    @Override
    public List<ScheduleLine> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new ScheduleLineRowMapper());
    }

    @Override
    public ScheduleLine getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[]{id},
                new ScheduleLineRowMapper());
    }
}
