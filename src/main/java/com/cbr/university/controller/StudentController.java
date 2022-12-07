package com.cbr.university.controller;

import com.cbr.university.model.Group;
import com.cbr.university.model.Student;
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
@RequestMapping("editing/students")
@Validated
public class StudentController {
    private final BaseService<Student> studentService;
    private final BaseService<Group> groupService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute(studentService.getAll());
        return "editing/students/view";
    }

    @GetMapping("get-add-view")
    public String getAddView(Model model) {
        model.addAttribute(new Student());
        model.addAttribute(groupService.getAll());
        return "editing/students/add";
    }

    @PostMapping("save-add-view")
    public String saveAddView(@Validated(RequestUI.class) Student student, BindingResult result) {
        if (!result.hasErrors()) {
            studentService.create(student);
            return "redirect:/editing/students";
        } else {
            return "redirect:/editing/students/get-add-view";
        }
    }

    @GetMapping("get-edit-view/{id}")
    public String getEditView(@PathVariable int id, Model model) {
        model.addAttribute(studentService.getById(id));
        model.addAttribute(groupService.getAll());
        return "editing/students/edit";
    }

    @PostMapping("save-edit-view")
    public String saveEditView(@Validated(RequestUI.class) Student student, BindingResult result) {
        if (!result.hasErrors()) {
            studentService.update(student);
            return "redirect:/editing/students";
        } else {
            return "redirect:/editing/students/get-edit-view/{id}";
        }
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        studentService.deleteById(id);
        return "redirect:/editing/students";
    }
}
