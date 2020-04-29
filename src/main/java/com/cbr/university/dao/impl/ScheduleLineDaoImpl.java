package com.cbr.university.dao.impl;

import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

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
    private static final String SQL_GET_ALL = "SELECT schedule_line_id, schedule_line_date, lesson_pair, group_id, teacher_id, course_id, room_id FROM schedule_lines";
    private static final String SQL_GET_BY_ID = "SELECT schedule_line_id, schedule_line_date, lesson_pair, group_id, teacher_id, course_id, room_id FROM schedule_lines WHERE schedule_line_id = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleLineDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(ScheduleLine scheduleLine) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
            ps.setObject(1, scheduleLine.getDate());
            ps.setString(2, scheduleLine.getLessonPair().getPairStartTime());
            ps.setInt(3, scheduleLine.getGroupId());
            ps.setInt(4, scheduleLine.getTeacherId());
            ps.setInt(5, scheduleLine.getCourseId());
            ps.setInt(6, scheduleLine.getRoomId());
            return ps;
        }, keyHolder);

        int scheduleLineId = keyHolder.getKey().intValue();
        scheduleLine.setId(scheduleLineId);
    }

    @Override
    public void update(ScheduleLine scheduleLine) {
        jdbcTemplate.update(SQL_UPDATE, scheduleLine.getDate(),
                scheduleLine.getLessonPair().getPairStartTime(), scheduleLine.getGroupId(),
                scheduleLine.getTeacherId(), scheduleLine.getCourseId(), scheduleLine.getRoomId(),
                scheduleLine.getId());
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
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id },
                new ScheduleLineRowMapper());
    }
}
