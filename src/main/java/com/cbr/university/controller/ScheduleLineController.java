package com.cbr.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.service.impl.ScheduleLineServiceImpl;

@Controller
@RequestMapping("schedule-lines")
public class ScheduleLineController {
    private static final String SCHEDULE_LINES_VIEW_NAME = "schedule-lines";
    private static final String SCHEDULE_LINES_MODEL_NAME = "scheduleLines";
    ModelAndView modelAndView = new ModelAndView();
    @Autowired
    ScheduleLineServiceImpl scheduleLineServiceImpl;
    
    @GetMapping
    public ModelAndView getAll() {
        modelAndView.clear();
        modelAndView.setViewName(SCHEDULE_LINES_VIEW_NAME);
        modelAndView.addObject(SCHEDULE_LINES_MODEL_NAME, scheduleLineServiceImpl.getAll());
        return modelAndView;
    }
}
