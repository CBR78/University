package com.cbr.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.model.ScheduleLine;
import com.cbr.university.service.impl.ScheduleLineServiceImpl;

@Controller
@RequestMapping("schedule-lines")
public class ScheduleLineController {
    private static final String SCHEDULE_LINES = "schedule-lines";
    private static final String SCHEDULE_LINES_ADD = "schedule-line-add";
    private static final String SCHEDULE_LINES_EDIT = "schedule-line-edit";
    @Autowired
    private ScheduleLineServiceImpl scheduleLineServiceImpl;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(SCHEDULE_LINES);
        mv.addObject(SCHEDULE_LINES, scheduleLineServiceImpl.getAll());
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(SCHEDULE_LINES_ADD);
        return mv;
    }

    @PostMapping("/add")
    public ModelAndView add(String name) {
        ScheduleLine scheduleLine = new ScheduleLine();
        // scheduleLine.setName(name);
        scheduleLineServiceImpl.create(scheduleLine);

        ModelAndView mv = new ModelAndView();
        mv.setViewName(SCHEDULE_LINES);
        mv.addObject(SCHEDULE_LINES, scheduleLineServiceImpl.getAll());
        return mv;
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable(value = "id") Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(SCHEDULE_LINES_EDIT);
        mv.addObject("scheduleLine", scheduleLineServiceImpl.getById(id));
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(@PathVariable(value = "id") Integer id, ScheduleLine scheduleLine) {
        scheduleLineServiceImpl.update(scheduleLine);

        ModelAndView mv = new ModelAndView();
        mv.setViewName(SCHEDULE_LINES);
        mv.addObject(SCHEDULE_LINES, scheduleLineServiceImpl.getAll());
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id) {
        scheduleLineServiceImpl.delete(scheduleLineServiceImpl.getById(id));

        ModelAndView mv = new ModelAndView();
        mv.setViewName(SCHEDULE_LINES);
        mv.addObject(SCHEDULE_LINES, scheduleLineServiceImpl.getAll());
        return mv;
    }
}
