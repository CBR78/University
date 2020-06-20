package com.cbr.university.controller;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.model.Group;
import com.cbr.university.model.Student;
import com.cbr.university.service.BaseService;

@Controller
@RequestMapping("students")
public class StudentController {
    private static final String STUDENTS = "students";
    private static final String STUDENTS_ADD = "student-add";
    private static final String STUDENTS_EDIT = "student-edit";
    private BaseService<Student> studentService;
    private BaseService<Group> groupService;
    private EntityManager entityManager;
    private ModelAndView mv = new ModelAndView();

    @Autowired
    public StudentController(BaseService<Student> studentService, BaseService<Group> groupService,
            EntityManager entityManager) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.entityManager = entityManager;
    }

    @GetMapping
    public ModelAndView getAll() {
        entityManager.clear();
        mv.clear();
        mv.setViewName(STUDENTS);
        mv.addObject(STUDENTS, studentService.getAll());
        return mv;
    }

    @GetMapping("add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName(STUDENTS_ADD);
        mv.addObject("groups", groupService.getAll());
        return mv;
    }

    @PostMapping("add")
    public ModelAndView add(Student student, BindingResult result) {
        studentService.create(student);
        return getAll();
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        mv.clear();
        mv.setViewName(STUDENTS_EDIT);
        mv.addObject("student", studentService.getById(id));
        mv.addObject("groups", groupService.getAll());
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(Student student) {
        studentService.update(student);
        return getAll();
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        studentService.delete(studentService.getById(id));
        return getAll();
    }
}
