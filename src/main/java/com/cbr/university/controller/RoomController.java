package com.cbr.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.service.impl.RoomServiceImpl;

@Controller
@RequestMapping("rooms")
public class RoomController {
    private static final String ROOMS_VIEW_NAME = "rooms";
    private static final String ROOMS_MODEL_NAME = "rooms";
    ModelAndView modelAndView = new ModelAndView();
    @Autowired
    RoomServiceImpl roomServiceImpl;
    
    @GetMapping
    public ModelAndView getAll() {
        modelAndView.clear();
        modelAndView.setViewName(ROOMS_VIEW_NAME);
        modelAndView.addObject(ROOMS_MODEL_NAME, roomServiceImpl.getAll());
        return modelAndView;
    }
}
