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

import com.cbr.university.model.Group;
import com.cbr.university.model.Student;
import com.cbr.university.model.dto.StudentDtoRest;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.group.RequestUI;

@Controller
@RequestMapping("students")
@Validated
public class StudentController {
    private static final String STUDENT = "student";
    private static final String GROUPS = "groups";
    private ModelAndView mv = new ModelAndView();
    private ModelMapper modelMapper;
    private BaseService<Student> studentService;
    private BaseService<Group> groupService;

    @Autowired
    public StudentController(BaseService<Student> studentService, BaseService<Group> groupService,
            ModelMapper modelMapper) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ModelAndView getAll() {
        mv.clear();
        mv.setViewName("students");
        mv.addObject("students", studentService.getAll());
        return mv;
    }

    @GetMapping("add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName("student-add");
        mv.addObject(STUDENT, new Student());
        mv.addObject(GROUPS, groupService.getAll());
        return mv;
    }

    @PostMapping("add")
    public ModelAndView add(@Validated(RequestUI.class) StudentDtoRest studentDtoRest,
            BindingResult result) {
        Student student = modelMapper.map(studentDtoRest, Student.class);
        if (result.hasErrors()) {
            mv.clear();
            mv.setViewName("student-add");
            mv.addObject(STUDENT, student);
            mv.addObject(GROUPS, groupService.getAll());
            return mv;
        } else {
            studentService.create(student);
            return getAll();
        }
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        mv.clear();
        mv.setViewName("student-edit");
        mv.addObject(STUDENT, studentService.getById(id));
        mv.addObject(GROUPS, groupService.getAll());
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(@Validated(RequestUI.class) StudentDtoRest studentDtoRest,
            BindingResult result) {
        Student student = modelMapper.map(studentDtoRest, Student.class);
        if (result.hasErrors()) {
            mv.clear();
            mv.setViewName("student-edit");
            mv.addObject(STUDENT, student);
            mv.addObject(GROUPS, groupService.getAll());
            return mv;
        } else {
            studentService.update(student);
            return getAll();
        }
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        studentService.delete(studentService.getById(id));
        return getAll();
    }
}
