package com.cbr.university.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import com.cbr.university.model.Course;
import com.cbr.university.model.Group;
import com.cbr.university.model.LessonPair;
import com.cbr.university.model.Room;
import com.cbr.university.model.ScheduleLine;
import com.cbr.university.model.Teacher;

public class ScheduleLineRowMapper implements RowMapper<ScheduleLine> {

    @Override
    public ScheduleLine mapRow(ResultSet resultSet, int i) throws SQLException {
        ScheduleLine scheduleLine = new ScheduleLine();

        scheduleLine.setId(resultSet.getInt("schedule_line_id"));
        scheduleLine.setDate(resultSet.getObject("schedule_line_date", LocalDate.class));
        scheduleLine.setLessonPair(LessonPair.valueOf(resultSet.getString("lesson_pair")));

        Group group = new Group();
        group.setId(resultSet.getInt("groups_group_id"));
        group.setName(resultSet.getString("groups_group_name"));
        scheduleLine.setGroup(group);

        Room room = new Room();
        room.setId(resultSet.getInt("rooms_room_id"));
        room.setName(resultSet.getString("rooms_room_name"));
        scheduleLine.setRoom(room);

        Course course = new Course();
        course.setId(resultSet.getInt("courses_course_id"));
        course.setName(resultSet.getString("courses_course_name"));

        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getInt("teachers_teacher_id"));
        teacher.setFirstName(resultSet.getString("teachers_teacher_first_name"));
        teacher.setLastName(resultSet.getString("teachers_teacher_last_name"));
        teacher.setCourse(course);
        scheduleLine.setTeacher(teacher);

        return scheduleLine;
    }
}
