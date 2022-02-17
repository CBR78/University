package com.cbr.university.controller;

import com.cbr.university.model.Course;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.group.RequestUI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("editing/courses")
@Validated
public class CourseController {
    private final BaseService<Course> courseService;

    public CourseController(BaseService<Course> courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute(courseService.getAll());
        return "editing/courses/view";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute(new Course());
        return "editing/courses/add";
    }

    @PostMapping("add")
    public String add(@Validated(RequestUI.class) Course course, BindingResult result) {
        if (!result.hasErrors()) {
            courseService.create(course);
            return "redirect:/editing/courses";
        } else {
            return "redirect:/editing/courses/add";
        }
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute(courseService.getById(id));
        return "editing/courses/edit";
    }

    @PostMapping("edit")
    public String edit(@Validated(RequestUI.class) Course course, BindingResult result) {
        if (!result.hasErrors()) {
            courseService.update(course);
            return "redirect:/editing/courses";
        } else {
            return "redirect:/editing/courses/edit";
        }
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        courseService.deleteById(id);
        return "redirect:/editing/courses";
    }
}
