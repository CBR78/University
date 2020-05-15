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
    private CourseServiceImpl courseServiceImpl;
    private ModelAndView mv = new ModelAndView();

    @Autowired
    public CourseController(CourseServiceImpl courseServiceImpl) {
        this.courseServiceImpl = courseServiceImpl;
    }

    @GetMapping
    public ModelAndView getAll() {
        mv.clear();
        mv.setViewName(COURSES);
        mv.addObject(COURSES, courseServiceImpl.getAll());
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName(COURSE_ADD);
        return mv;
    }

    @PostMapping("/add")
    public ModelAndView add(Course course, BindingResult result) {
        courseServiceImpl.create(course);
        return getAll();
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id) {
        mv.clear();
        mv.setViewName(COURSE_EDIT);
        mv.addObject("course", courseServiceImpl.getById(id));
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id, Course course) {
        courseServiceImpl.update(course);
        return getAll();
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        courseServiceImpl.delete(courseServiceImpl.getById(id));
        return getAll();
    }
}
