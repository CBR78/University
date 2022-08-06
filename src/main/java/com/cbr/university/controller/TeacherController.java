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

    @GetMapping("get-add-view")
    public String getAddView(Model model) {
        model.addAttribute(new Teacher());
        model.addAttribute(courseService.getAll());
        return "editing/teachers/add";
    }

    @PostMapping("save-add-view")
    public String saveAddView(@Validated(RequestUI.class) Teacher teacher, BindingResult result) {
        if (!result.hasErrors()) {
            teacherService.create(teacher);
            return "redirect:/editing/teachers";
        } else {
            return "redirect:/editing/teachers/get-add-view";
        }
    }

    @GetMapping("get-edit-view/{id}")
    public String getEditView(@PathVariable int id, Model model) {
        model.addAttribute(teacherService.getById(id));
        model.addAttribute(courseService.getAll());
        return "editing/teachers/edit";
    }

    @PostMapping("save-edit-view")
    public String saveEditView(@Validated(RequestUI.class) Teacher teacher, BindingResult result) {
        if (!result.hasErrors()) {
            teacherService.update(teacher);
            return "redirect:/editing/teachers";
        } else {
            return "redirect:/editing/teachers/get-edit-view/{id}";
        }
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        teacherService.deleteById(id);
        return "redirect:/editing/teachers";
    }
}
