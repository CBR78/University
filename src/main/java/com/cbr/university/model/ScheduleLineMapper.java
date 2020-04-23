package com.cbr.university.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

public class ScheduleLineMapper implements RowMapper<ScheduleLine> {

    public ScheduleLine mapRow(ResultSet resultSet, int i) throws SQLException {
        ScheduleLine scheduleLine = new ScheduleLine();
        scheduleLine.setId(resultSet.getInt("schedule_line_id"));
        scheduleLine.setDate(resultSet.getObject("schedule_line_date", LocalDate.class));
        return scheduleLine;
    }
}
