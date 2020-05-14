package com.cbr.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.model.Student;
import com.cbr.university.service.impl.GroupServiceImpl;
import com.cbr.university.service.impl.StudentServiceImpl;

@Controller
@RequestMapping("students")
public class StudentController {
    private static final String STUDENTS = "students";
    private static final String STUDENTS_ADD = "student-add";
    private static final String STUDENTS_EDIT = "student-edit";
    @Autowired
    private StudentServiceImpl studentServiceImpl;
    @Autowired
    private GroupServiceImpl groupServiceImpl;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(STUDENTS);
        mv.addObject(STUDENTS, studentServiceImpl.getAll());
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(STUDENTS_ADD);
        mv.addObject("groups", groupServiceImpl.getAll());
        return mv;
    }

    @PostMapping("/add")
    public ModelAndView add(Student student, BindingResult result) {
        studentServiceImpl.create(student);

        ModelAndView mv = new ModelAndView();
        mv.setViewName(STUDENTS);
        mv.addObject(STUDENTS, studentServiceImpl.getAll());
        return mv;
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(STUDENTS_EDIT);
        mv.addObject("student", studentServiceImpl.getById(id));
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id, Student student) {
        studentServiceImpl.update(student);

        ModelAndView mv = new ModelAndView();
        mv.setViewName(STUDENTS);
        mv.addObject(STUDENTS, studentServiceImpl.getAll());
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        studentServiceImpl.delete(studentServiceImpl.getById(id));

        ModelAndView mv = new ModelAndView();
        mv.setViewName(STUDENTS);
        mv.addObject(STUDENTS, studentServiceImpl.getAll());
        return mv;
    }
}
