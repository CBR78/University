package com.cbr.university.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cbr.university.model.Course;
import com.cbr.university.model.Group;
import com.cbr.university.model.Room;
import com.cbr.university.model.ScheduleLine;
import com.cbr.university.model.Student;
import com.cbr.university.model.Teacher;
import com.cbr.university.service.BaseService;

public class IdExistsInDbValidator implements ConstraintValidator<IdExistsInDb, Integer> {

    private BaseService<Course> courseService;
    private BaseService<Group> groupService;
    private BaseService<Room> roomService;
    private BaseService<ScheduleLine> scheduleLineService;
    private BaseService<Student> studentService;
    private BaseService<Teacher> teacherService;
    private BaseService<?> currentService;

    @Autowired
    private IdExistsInDbValidator(BaseService<Course> courseService, BaseService<Group> groupService,
            BaseService<Room> roomService, BaseService<ScheduleLine> scheduleLineService,
            BaseService<Student> studentService, BaseService<Teacher> teacherService) {
        this.courseService = courseService;
        this.groupService = groupService;
        this.roomService = roomService;
        this.scheduleLineService = scheduleLineService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @Override
    public void initialize(IdExistsInDb exist) {
        String typeObject = exist.typeObject();

        switch (typeObject) {
        case "Course":
            this.currentService = courseService;
            break;
        case ("Group"):
            this.currentService = groupService;
            break;
        case ("Room"):
            this.currentService = roomService;
            break;
        case ("ScheduleLine"):
            this.currentService = scheduleLineService;
            break;
        case ("Student"):
            this.currentService = studentService;
            break;
        case ("Teacher"):
            this.currentService = teacherService;
            break;
        default:
            throw new IllegalArgumentException(
                    "typeObject parameter accepts only 1 of 6 values - Course, Group, Room, ScheduleLine, Student, Teacher");
        }
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        if (id == null) {
            return true;
        }

        return currentService.existsById(id);
    }
}
