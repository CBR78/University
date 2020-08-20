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

import com.cbr.university.model.Group;
import com.cbr.university.model.dto.GroupDtoRest;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.group.RequestUI;

@Controller
@RequestMapping("groups")
@Validated
public class GroupController {
    private static final String GROUP = "group";
    private ModelAndView mv = new ModelAndView();
    private ModelMapper modelMapper;
    private BaseService<Group> groupService;

    @Autowired
    public GroupController(BaseService<Group> groupService, ModelMapper modelMapper) {
        this.groupService = groupService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ModelAndView getAll() {
        mv.clear();
        mv.setViewName("groups/view");
        mv.addObject("groups", groupService.getAll());
        return mv;
    }

    @GetMapping("add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName("groups/add");
        mv.addObject(GROUP, new Group());
        return mv;
    }

    @PostMapping("add")
    public ModelAndView add(@Validated(RequestUI.class) GroupDtoRest groupDtoRest,
            BindingResult result) {
        Group group = modelMapper.map(groupDtoRest, Group.class);
        if (result.hasErrors()) {
            mv.clear();
            mv.setViewName("groups/add");
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
        mv.setViewName("groups/edit");
        mv.addObject(GROUP, groupService.getById(id));
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(@Validated(RequestUI.class) GroupDtoRest groupDtoRest,
            BindingResult result) {
        Group group = modelMapper.map(groupDtoRest, Group.class);
        if (result.hasErrors()) {
            mv.clear();
            mv.setViewName("groups/edit");
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
