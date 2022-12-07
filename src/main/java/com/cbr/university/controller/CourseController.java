package com.cbr.university.controller;

import com.cbr.university.model.Course;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.group.RequestUI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("editing/courses")
@Validated
public class CourseController {
    private final BaseService<Course> courseService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute(courseService.getAll());
        return "editing/courses/view";
    }

    @GetMapping("get-add-view")
    public String getAddView(Model model) {
        model.addAttribute(new Course());
        return "editing/courses/add";
    }

    @PostMapping("save-add-view")
    public String saveAddView(@Validated(RequestUI.class) Course course, BindingResult result) {
        if (!result.hasErrors()) {
            courseService.create(course);
            return "redirect:/editing/courses";
        } else {
            return "redirect:/editing/courses/get-add-view";
        }
    }

    @GetMapping("get-edit-view/{id}")
    public String getEditView(@PathVariable int id, Model model) {
        model.addAttribute(courseService.getById(id));
        return "editing/courses/edit";
    }

    @PostMapping("save-edit-view")
    public String saveEditView(@Validated(RequestUI.class) Course course, BindingResult result) {
        if (!result.hasErrors()) {
            courseService.update(course);
            return "redirect:/editing/courses";
        } else {
            return "redirect:/editing/courses/get-edit-view/{id}";
        }
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        courseService.deleteById(id);
        return "redirect:/editing/courses";
    }
}
