package com.cbr.university.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import com.cbr.university.dao.SpringConfig;
import com.cbr.university.dao.impl.CourseDaoImpl;
import com.cbr.university.dao.impl.GroupDaoImpl;
import com.cbr.university.dao.impl.RoomDaoImpl;
import com.cbr.university.dao.impl.TeacherDaoImpl;
import com.cbr.university.model.Course;
import com.cbr.university.model.Group;
import com.cbr.university.model.LessonPair;
import com.cbr.university.model.Room;
import com.cbr.university.model.ScheduleLine;
import com.cbr.university.model.Teacher;

public class ScheduleLineMapper implements RowMapper<ScheduleLine> {

    public ScheduleLine mapRow(ResultSet resultSet, int i) throws SQLException {
        ScheduleLine scheduleLine = new ScheduleLine();
        
        int scheduleLineId = resultSet.getInt("schedule_line_id");
        LocalDate scheduleLineDate = resultSet.getObject("schedule_line_date", LocalDate.class);
        String lessonPairStatus = resultSet.getString("lesson_pair");
        int groupId = resultSet.getInt("group_id");
        int teacherId = resultSet.getInt("teacher_id");
        int courseId = resultSet.getInt("course_id");
        int roomId = resultSet.getInt("room_id");

        scheduleLine.setId(scheduleLineId);
        scheduleLine.setDate(scheduleLineDate);
        scheduleLine.setLessonPair(LessonPair.valueOf(lessonPairStatus));
        scheduleLine.setGroup(getGroupById(groupId));
        scheduleLine.setTeacher(getTeacherById(teacherId));
        scheduleLine.setСourse(getCourseById(courseId));
        scheduleLine.setRoom(getRoomById(roomId));
        return scheduleLine;
    }

    private Group getGroupById(int groupId) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class);
        GroupDaoImpl groupDaoImpl = context.getBean(GroupDaoImpl.class);

        Group group = groupDaoImpl.getById(groupId);

        context.close();
        return group;
    }

    private Teacher getTeacherById(int teacherId) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class);
        TeacherDaoImpl teacherDaoImpl = context.getBean(TeacherDaoImpl.class);

        Teacher teacher = teacherDaoImpl.getById(teacherId);

        context.close();
        return teacher;
    }

    private Course getCourseById(int courseId) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class);
        CourseDaoImpl courseDaoImpl = context.getBean(CourseDaoImpl.class);

        Course course = courseDaoImpl.getById(courseId);

        context.close();
        return course;
    }

    private Room getRoomById(int roomId) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class);
        RoomDaoImpl roomDaoImpl = context.getBean(RoomDaoImpl.class);

        Room room = roomDaoImpl.getById(roomId);

        context.close();
        return room;
    }
}
