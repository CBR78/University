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

import com.cbr.university.model.Course;
import com.cbr.university.model.dto.CourseDtoRest;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.group.RequestUI;

@Controller
@RequestMapping("editing/courses")
@Validated
public class CourseController {
    private static final String COURSE = "course";
    private ModelAndView mv = new ModelAndView();
    private ModelMapper modelMapper;
    private BaseService<Course> courseService;

    @Autowired
    public CourseController(BaseService<Course> courseService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.modelMapper = modelMapper;
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
        mv.addObject(COURSE, new Course());
        return mv;
    }

    @PostMapping("add")
    public ModelAndView add(@Validated(RequestUI.class) CourseDtoRest courseDtoRest,
            BindingResult result) {
        Course course = modelMapper.map(courseDtoRest, Course.class);
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

    @PostMapping("edit/{id}")
    public ModelAndView edit(@Validated(RequestUI.class) CourseDtoRest courseDtoRest,
            BindingResult result) {
        Course course = modelMapper.map(courseDtoRest, Course.class);
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
