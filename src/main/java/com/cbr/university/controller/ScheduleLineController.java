package com.cbr.university.controller;

import com.cbr.university.model.Group;
import com.cbr.university.model.Room;
import com.cbr.university.model.ScheduleLine;
import com.cbr.university.model.Teacher;
import com.cbr.university.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("editing/schedule-lines")
public class ScheduleLineController {
    private static final String SCHEDULELINE = "scheduleLine";
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
    public String getAll(Model model) {
        model.addAttribute("scheduleLines", scheduleLineService.getAll());
        return "editing/schedule-lines/view";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute(SCHEDULELINE, ScheduleLine.class);
        model.addAttribute("groups", groupService.getAll());
        model.addAttribute("teachers", teacherService.getAll());
        model.addAttribute("rooms", roomService.getAll());
        return "editing/schedule-lines/add";
    }

    @PostMapping("add")
    public String add(ScheduleLine scheduleLine, Model model) {
        scheduleLineService.create(scheduleLine);
        return getAll(model);
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute(SCHEDULELINE, scheduleLineService.getById(id));
        model.addAttribute("groups", groupService.getAll());
        model.addAttribute("teachers", teacherService.getAll());
        model.addAttribute("rooms", roomService.getAll());
        return "editing/schedule-lines/edit";
    }

    @PostMapping("edit")
    public String edit(ScheduleLine scheduleLine, Model model) {
        scheduleLineService.update(scheduleLine);
        return getAll(model);
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        scheduleLineService.deleteById(id);
        return getAll(model);
    }
}
