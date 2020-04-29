package com.cbr.university.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import com.cbr.university.model.LessonPair;
import com.cbr.university.model.ScheduleLine;

public class ScheduleLineRowMapper implements RowMapper<ScheduleLine> {

    @Override
    public ScheduleLine mapRow(ResultSet resultSet, int i) throws SQLException {
        ScheduleLine scheduleLine = new ScheduleLine();

        scheduleLine.setId(resultSet.getInt("schedule_line_id"));
        scheduleLine.setDate(resultSet.getObject("schedule_line_date", LocalDate.class));
        scheduleLine.setLessonPair(LessonPair.valueOf(resultSet.getString("lesson_pair")));
        scheduleLine.setGroupId(resultSet.getInt("group_id"));
        scheduleLine.setTeacherId(resultSet.getInt("teacher_id"));
        scheduleLine.setCourseId(resultSet.getInt("course_id"));
        scheduleLine.setRoomId(resultSet.getInt("room_id"));

        return scheduleLine;
    }
}
