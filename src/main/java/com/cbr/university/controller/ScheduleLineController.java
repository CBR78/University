package com.cbr.university.controller;

import com.cbr.university.model.Group;
import com.cbr.university.model.Room;
import com.cbr.university.model.ScheduleLine;
import com.cbr.university.model.Teacher;
import com.cbr.university.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("editing/schedule-lines")
public class ScheduleLineController {
    private static final String SCHEDULELINE = "scheduleLine";
    private final ModelAndView mv = new ModelAndView();
    private final BaseService<ScheduleLine> scheduleLineService;
    private final BaseService<Group> groupService;
    private final BaseService<Room> roomService;
    private final BaseService<Teacher> teacherService;

    public ScheduleLineController(BaseService<ScheduleLine> scheduleLineService, BaseService<Group> groupService,
                                  BaseService<Room> roomService, BaseService<Teacher> teacherService) {
        this.scheduleLineService = scheduleLineService;
        this.groupService = groupService;
        this.roomService = roomService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public ModelAndView getAll() {
        mv.clear();
        mv.setViewName("editing/schedule-lines/view");
        mv.addObject("scheduleLines", scheduleLineService.getAll());
        return mv;
    }

    @GetMapping("add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName("editing/schedule-lines/add");
        mv.addObject(SCHEDULELINE, ScheduleLine.class);
        mv.addObject("groups", groupService.getAll());
        mv.addObject("teachers", teacherService.getAll());
        mv.addObject("rooms", roomService.getAll());
        return mv;
    }

    @PostMapping("add")
    public ModelAndView add(ScheduleLine scheduleLine) {
        scheduleLineService.create(scheduleLine);
        return getAll();
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        mv.clear();
        mv.setViewName("editing/schedule-lines/edit");
        mv.addObject(SCHEDULELINE, scheduleLineService.getById(id));
        mv.addObject("groups", groupService.getAll());
        mv.addObject("teachers", teacherService.getAll());
        mv.addObject("rooms", roomService.getAll());
        return mv;
    }

    @PostMapping("edit")
    public ModelAndView edit(ScheduleLine scheduleLine) {
        scheduleLineService.update(scheduleLine);
        return getAll();
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        scheduleLineService.deleteById(id);
        return getAll();
    }
}
