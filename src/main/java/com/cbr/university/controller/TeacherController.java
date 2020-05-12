package com.cbr.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.service.impl.TeacherServiceImpl;

@Controller
@RequestMapping("teachers")
public class TeacherController {
    private static final String TEACHERS_VIEW_NAME = "teachers";
    private static final String TEACHERS_MODEL_NAME = "teachers";
    ModelAndView modelAndView = new ModelAndView();
    @Autowired
    TeacherServiceImpl teacherServiceImpl;
    
    @GetMapping
    public ModelAndView getAll() {
        modelAndView.clear();
        modelAndView.setViewName(TEACHERS_VIEW_NAME);
        modelAndView.addObject(TEACHERS_MODEL_NAME, teacherServiceImpl.getAll());
        return modelAndView;
    }
}
