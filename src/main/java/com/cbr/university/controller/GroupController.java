package com.cbr.university.controller;

import com.cbr.university.dto.GroupDto;
import com.cbr.university.model.Group;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.group.RequestUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("editing/groups")
@Validated
public class GroupController {
    private static final String GROUP = "group";
    private final ModelAndView mv = new ModelAndView();
    private final BaseService<Group> groupService;

    @Autowired
    public GroupController(BaseService<Group> groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ModelAndView getAll() {
        mv.clear();
        mv.setViewName("editing/groups/view");
        mv.addObject("groups", groupService.getAll());
        return mv;
    }

    @GetMapping("add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName("editing/groups/add");
        mv.addObject(GROUP, new Group());
        return mv;
    }

    @PostMapping("add")
    public ModelAndView add(@Validated(RequestUI.class) GroupDto groupDto,
            BindingResult result) {
        Group group = new Group(groupDto);
        if (result.hasErrors()) {
            mv.clear();
            mv.setViewName("editing/groups/add");
            mv.addObject(GROUP, group);
            return mv;
        } else {
            groupService.create(group);
            return getAll();
        }
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        mv.clear();
        mv.setViewName("editing/groups/edit");
        mv.addObject(GROUP, groupService.getById(id));
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(@Validated(RequestUI.class) GroupDto groupDto,
            BindingResult result) {
        Group group = new Group(groupDto);
        if (result.hasErrors()) {
            mv.clear();
            mv.setViewName("editing/groups/edit");
            mv.addObject(GROUP, group);
            return mv;
        } else {
            groupService.update(group);
            return getAll();
        }
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        groupService.delete(groupService.getById(id));
        return getAll();
    }
}
