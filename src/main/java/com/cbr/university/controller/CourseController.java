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
import com.cbr.university.service.BaseService;

@Controller
@RequestMapping("courses")
public class CourseController {
    private static final String COURSES = "courses";
    private static final String COURSE_ADD = "course-add";
    private static final String COURSE_EDIT = "course-edit";
    private BaseService<Course> courseService;
    private ModelAndView mv = new ModelAndView();

    @Autowired
    public CourseController(BaseService<Course> courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ModelAndView getAll() {
        mv.clear();
        mv.setViewName(COURSES);
        mv.addObject(COURSES, courseService.getAll());
        return mv;
    }

    @GetMapping("add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName(COURSE_ADD);
        return mv;
    }

    @PostMapping("add")
    public ModelAndView add(Course course, BindingResult result) {
        courseService.create(course);
        return getAll();
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        mv.clear();
        mv.setViewName(COURSE_EDIT);
        mv.addObject("course", courseService.getById(id));
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(Course course) {
        courseService.update(course);
        return getAll();
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        courseService.delete(courseService.getById(id));
        return getAll();
    }
}
