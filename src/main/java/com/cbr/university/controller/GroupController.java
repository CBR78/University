package com.cbr.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.service.impl.GroupServiceImpl;

@Controller
@RequestMapping("groups")
public class GroupController {
    private static final String GROUPS_VIEW_NAME = "groups";
    private static final String GROUPS_MODEL_NAME = "groups";
    ModelAndView modelAndView = new ModelAndView();
    @Autowired
    GroupServiceImpl groupServiceImpl;
    
    @GetMapping
    public ModelAndView getAll() {
        modelAndView.clear();
        modelAndView.setViewName(GROUPS_VIEW_NAME);
        modelAndView.addObject(GROUPS_MODEL_NAME, groupServiceImpl.getAll());
        return modelAndView;
    }
}
