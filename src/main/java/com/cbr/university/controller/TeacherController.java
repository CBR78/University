package com.cbr.university.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.model.Course;
import com.cbr.university.model.Teacher;
import com.cbr.university.model.dto.TeacherDtoRest;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.group.RequestUI;

@Controller
@RequestMapping("teachers")
@Validated
public class TeacherController {
    private static final String TEACHER = "teacher";
    private static final String COURSES = "courses";
    private ModelAndView mv = new ModelAndView();
    private ModelMapper modelMapper;
    private BaseService<Teacher> teacherService;
    private BaseService<Course> courseService;

    @Autowired
    public TeacherController(BaseService<Teacher> teacherService, BaseService<Course> courseService,
            ModelMapper modelMapper) {
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ModelAndView getAll() {
        mv.clear();
        mv.setViewName("teachers");
        mv.addObject("teachers", teacherService.getAll());
        return mv;
    }

    @GetMapping("add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName("teacher-add");
        mv.addObject(TEACHER, new Teacher());
        mv.addObject(COURSES, courseService.getAll());
        return mv;
    }

    @PostMapping("add")
    public ModelAndView add(@Validated(RequestUI.class) TeacherDtoRest teacherDtoRest,
            BindingResult result) {
        Teacher teacher = modelMapper.map(teacherDtoRest, Teacher.class);
        if (result.hasErrors()) {
            mv.clear();
            mv.setViewName("teacher-add");
            mv.addObject(TEACHER, teacher);
            mv.addObject(COURSES, courseService.getAll());
            return mv;
        } else {
            teacherService.create(teacher);
            return getAll();
        }
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        mv.clear();
        mv.setViewName("teacher-edit");
        mv.addObject(TEACHER, teacherService.getById(id));
        mv.addObject(COURSES, courseService.getAll());
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(@Validated(RequestUI.class) TeacherDtoRest teacherDtoRest,
            BindingResult result) {
        Teacher teacher = modelMapper.map(teacherDtoRest, Teacher.class);
        if (result.hasErrors()) {
            mv.clear();
            mv.setViewName("teacher-edit");
            mv.addObject(TEACHER, teacher);
            mv.addObject(COURSES, courseService.getAll());
            return mv;
        } else {
            teacherService.update(teacher);
            return getAll();
        }
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        teacherService.delete(teacherService.getById(id));
        return getAll();
    }
}
