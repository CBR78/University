package com.cbr.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.service.impl.CourseServiceImpl;

@Controller
@RequestMapping("courses")
public class CourseController {
    private static final String COURSES_VIEW_NAME = "courses";
    private static final String COURSES_MODEL_NAME = "courses";
    ModelAndView modelAndView = new ModelAndView();
    @Autowired
    CourseServiceImpl courseServiceImpl;
    
    @GetMapping
    public ModelAndView getAll() {
        modelAndView.clear();
        modelAndView.setViewName(COURSES_VIEW_NAME);
        modelAndView.addObject(COURSES_MODEL_NAME, courseServiceImpl.getAll());
        return modelAndView;
    }
}
