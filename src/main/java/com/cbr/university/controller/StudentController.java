package com.cbr.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.service.impl.StudentServiceImpl;

@Controller
@RequestMapping("students")
public class StudentController {
    private static final String STUDENTS_VIEW_NAME = "students";
    private static final String STUDENTS_MODEL_NAME = "students";
    ModelAndView modelAndView = new ModelAndView();
    @Autowired
    StudentServiceImpl studentServiceImpl;
    
    @GetMapping
    public ModelAndView getAll() {
        modelAndView.clear();
        modelAndView.setViewName(STUDENTS_VIEW_NAME);
        modelAndView.addObject(STUDENTS_MODEL_NAME, studentServiceImpl.getAll());
        return modelAndView;
    }
}
