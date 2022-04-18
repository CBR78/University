package com.cbr.university.validation;

import com.cbr.university.model.Course;
import com.cbr.university.model.Group;
import com.cbr.university.model.Room;
import com.cbr.university.model.ScheduleLine;
import com.cbr.university.model.Student;
import com.cbr.university.model.Teacher;
import com.cbr.university.service.BaseService;
import org.springframework.stereotype.Component;

@Component
public class ServiceSimpleFactory {

    private final BaseService<Course> courseService;
    private final BaseService<Group> groupService;
    private final BaseService<Room> roomService;
    private final BaseService<ScheduleLine> scheduleLineService;
    private final BaseService<Student> studentService;
    private final BaseService<Teacher> teacherService;

    ServiceSimpleFactory(BaseService<Course> courseService, BaseService<Group> groupService,
                         BaseService<Room> roomService, BaseService<ScheduleLine> scheduleLineService,
                         BaseService<Student> studentService, BaseService<Teacher> teacherService) {
        this.courseService = courseService;
        this.groupService = groupService;
        this.roomService = roomService;
        this.scheduleLineService = scheduleLineService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    public BaseService<?> load(String typeObject) {

        switch (typeObject) {
            case "Course":
                return this.courseService;
            case ("Group"):
                return this.groupService;
            case ("Room"):
                return this.roomService;
            case ("ScheduleLine"):
                return this.scheduleLineService;
            case ("Student"):
                return this.studentService;
            case ("Teacher"):
                return this.teacherService;
            default:
                throw new IllegalArgumentException(
                        "typeObject parameter accepts only 1 of 6 values - Course, Group, Room, ScheduleLine, Student, Teacher");
        }
    }
}
