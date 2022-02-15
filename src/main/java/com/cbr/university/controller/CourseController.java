package com.cbr.university.controller;

import com.cbr.university.model.Course;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.group.RequestUI;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("editing/courses")
@Validated
public class CourseController {
    private static final String COURSE = "course";
    private final ModelAndView mv = new ModelAndView();
    private final BaseService<Course> courseService;

    public CourseController(BaseService<Course> courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ModelAndView getAll() {
        mv.clear();
        mv.setViewName("editing/courses/view");
        mv.addObject("courses", courseService.getAll());
        return mv;
    }

    @GetMapping("add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName("editing/courses/add");
        mv.addObject(COURSE, Course.class);
        return mv;
    }

    @PostMapping("add")
    public ModelAndView add(@Validated(RequestUI.class) Course course, BindingResult result) {
        if (result.hasErrors()) {
            mv.clear();
            mv.setViewName("editing/courses/add");
            mv.addObject(COURSE, course);
            return mv;
        } else {
            courseService.create(course);
            return getAll();
        }
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        mv.clear();
        mv.setViewName("editing/courses/edit");
        mv.addObject(COURSE, courseService.getById(id));
        return mv;
    }

    @PostMapping("edit")
    public ModelAndView edit(@Validated(RequestUI.class) Course course, BindingResult result) {
        if (result.hasErrors()) {
            mv.clear();
            mv.setViewName("editing/courses/edit");
            mv.addObject(COURSE, course);
            return mv;
        } else {
            courseService.update(course);
            return getAll();
        }
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        courseService.delete(courseService.getById(id));
        return getAll();
    }
}
