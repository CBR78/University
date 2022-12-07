package com.cbr.university.validation;

import com.cbr.university.model.Course;
import com.cbr.university.model.Group;
import com.cbr.university.model.Room;
import com.cbr.university.model.ScheduleLine;
import com.cbr.university.model.Student;
import com.cbr.university.model.Teacher;
import com.cbr.university.service.BaseService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ServiceContext {

    private final Map<String, BaseService<?>> baseService = new HashMap<>();

    ServiceContext(BaseService<Course> courseService, BaseService<Group> groupService,
                   BaseService<Room> roomService, BaseService<ScheduleLine> scheduleLineService,
                   BaseService<Student> studentService, BaseService<Teacher> teacherService) {
        this.baseService.put("Course", courseService);
        this.baseService.put("Group", groupService);
        this.baseService.put("Room", roomService);
        this.baseService.put("ScheduleLine", scheduleLineService);
        this.baseService.put("Student", studentService);
        this.baseService.put("Teacher", teacherService);
    }

    public Optional<BaseService<?>> getInstance(String typeObject) {
        return Optional.ofNullable(baseService.get(typeObject));
    }
}
