package com.cbr.university.controller;

import com.cbr.university.model.Group;
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
@RequestMapping("editing/groups")
@Validated
public class GroupController {
    private final BaseService<Group> groupService;

    public GroupController(BaseService<Group> groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute(groupService.getAll());
        return "editing/groups/view";
    }

    @GetMapping("get-add-view")
    public String getAddView(Model model) {
        model.addAttribute(new Group());
        return "editing/groups/add";
    }

    @PostMapping("save-add-view")
    public String saveAddView(@Validated(RequestUI.class) Group group, BindingResult result) {
        if (!result.hasErrors()) {
            groupService.create(group);
            return "redirect:/editing/groups";
        } else {
            return "redirect:/editing/groups/get-add-view";
        }
    }

    @GetMapping("get-edit-view/{id}")
    public String getEditView(@PathVariable int id, Model model) {
        model.addAttribute(groupService.getById(id));
        return "editing/groups/edit";
    }

    @PostMapping("save-edit-view")
    public String saveEditView(@Validated(RequestUI.class) Group group, BindingResult result) {
        if (!result.hasErrors()) {
            groupService.update(group);
            return "redirect:/editing/groups";
        } else {
            return "redirect:/editing/groups/get-edit-view/{id}";
        }
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        groupService.deleteById(id);
        return "redirect:/editing/groups";
    }
}
