package com.cbr.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.model.ScheduleLine;
import com.cbr.university.service.impl.GroupServiceImpl;
import com.cbr.university.service.impl.RoomServiceImpl;
import com.cbr.university.service.impl.ScheduleLineServiceImpl;
import com.cbr.university.service.impl.TeacherServiceImpl;

@Controller
@RequestMapping("schedule-lines")
public class ScheduleLineController {
    private static final String SCHEDULE_LINES = "schedule-lines";
    private static final String SCHEDULE_LINES_ADD = "schedule-line-add";
    private static final String SCHEDULE_LINES_EDIT = "schedule-line-edit";
    private ModelAndView mv = new ModelAndView();
    @Autowired
    private ScheduleLineServiceImpl scheduleLineServiceImpl;
    @Autowired
    private GroupServiceImpl groupServiceImpl;
    @Autowired
    private RoomServiceImpl roomServiceImpl;
    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @GetMapping
    public ModelAndView getAll() {
        mv.clear();
        mv.setViewName(SCHEDULE_LINES);
        mv.addObject("scheduleLines", scheduleLineServiceImpl.getAll());
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName(SCHEDULE_LINES_ADD);
        mv.addObject("groups", groupServiceImpl.getAll());
        mv.addObject("rooms", roomServiceImpl.getAll());
        mv.addObject("teachers", teacherServiceImpl.getAll());
        return mv;
    }

    @PostMapping("/add")
    public ModelAndView add(ScheduleLine scheduleLine, BindingResult result) {
        scheduleLineServiceImpl.create(scheduleLine);
        return getAll();
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id) {
        mv.clear();
        mv.setViewName(SCHEDULE_LINES_EDIT);
        mv.addObject("scheduleLine", scheduleLineServiceImpl.getById(id));
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(ScheduleLine scheduleLine, BindingResult result) {
        scheduleLineServiceImpl.update(scheduleLine);
        return getAll();
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        scheduleLineServiceImpl.delete(scheduleLineServiceImpl.getById(id));
        return getAll();
    }
}
