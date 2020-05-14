package com.cbr.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.model.Course;
import com.cbr.university.service.impl.CourseServiceImpl;

@Controller
@RequestMapping("courses")
public class CourseController {
    private static final String COURSES = "courses";
    private static final String COURSE_ADD = "course-add";
    private static final String COURSE_EDIT = "course-edit";
    @Autowired
    private CourseServiceImpl courseServiceImpl;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(COURSES);
        mv.addObject(COURSES, courseServiceImpl.getAll());
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(COURSE_ADD);
        mv.addObject("course", new Course());
        return mv;
    }

    @PostMapping("/add")
    public ModelAndView add(Course course, BindingResult result) {
        courseServiceImpl.create(course);

        ModelAndView mv = new ModelAndView();
        mv.setViewName(COURSES);
        mv.addObject(COURSES, courseServiceImpl.getAll());
        return mv;
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(COURSE_EDIT);
        mv.addObject("course", courseServiceImpl.getById(id));
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id, Course course) {
        courseServiceImpl.update(course);

        ModelAndView mv = new ModelAndView();
        mv.setViewName(COURSES);
        mv.addObject(COURSES, courseServiceImpl.getAll());
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        courseServiceImpl.delete(courseServiceImpl.getById(id));

        ModelAndView mv = new ModelAndView();
        mv.setViewName(COURSES);
        mv.addObject(COURSES, courseServiceImpl.getAll());
        return mv;
    }
}
