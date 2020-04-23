package com.cbr.university.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cbr.university.model.ScheduleLine;
import com.cbr.university.model.ScheduleLineMapper;

@Component
public class ScheduleLineDaoImpl implements BaseDao<ScheduleLine> {
    private static final String SQL_INSERT = "INSERT INTO schedule_lines (schedule_line_date) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE schedule_lines SET schedule_line_date = ? WHERE schedule_line_id = ?";
    private static final String SQL_DELETE = "DELETE FROM schedule_lines WHERE schedule_line_id = ?";
    private static final String SQL_GET_ALL = "SELECT * FROM schedule_lines";
    private static final String SQL_GET_BY_ID = "SELECT * FROM schedule_lines WHERE schedule_line_id = ?";
    JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleLineDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean create(ScheduleLine scheduleLine) {
        return jdbcTemplate.update(SQL_INSERT, scheduleLine.getDate()) > 0;
    }

    public boolean update(ScheduleLine scheduleLine) {
        return jdbcTemplate.update(SQL_UPDATE, scheduleLine.getDate(), scheduleLine.getId()) > 0;
    }

    public boolean delete(ScheduleLine scheduleLine) {
        return jdbcTemplate.update(SQL_DELETE, scheduleLine.getId()) > 0;
    }

    public List<ScheduleLine> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new ScheduleLineMapper());
    }

    public ScheduleLine getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new ScheduleLineMapper());
    }
}
