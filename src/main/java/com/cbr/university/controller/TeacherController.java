package com.cbr.university.controller;

import com.cbr.university.model.Course;
import com.cbr.university.model.Teacher;
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
@RequestMapping("editing/teachers")
@Validated
public class TeacherController {
    private final BaseService<Teacher> teacherService;
    private final BaseService<Course> courseService;

    public TeacherController(BaseService<Teacher> teacherService, BaseService<Course> courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute(teacherService.getAll());
        return "editing/teachers/view";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute(new Teacher());
        model.addAttribute(courseService.getAll());
        return "editing/teachers/add";
    }

    @PostMapping("add")
    public String add(@Validated(RequestUI.class) Teacher teacher, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/editing/teachers/add";
        } else {
            teacherService.create(teacher);
            return "redirect:/editing/teachers";
        }
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute(teacherService.getById(id));
        model.addAttribute(courseService.getAll());
        return "editing/teachers/edit";
    }

    @PostMapping("edit")
    public String edit(@Validated(RequestUI.class) Teacher teacher, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/editing/teachers/edit";
        } else {
            teacherService.update(teacher);
            return "redirect:/editing/teachers";
        }
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        teacherService.deleteById(id);
        return "redirect:/editing/teachers";
    }
}
