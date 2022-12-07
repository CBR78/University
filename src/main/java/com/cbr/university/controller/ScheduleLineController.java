package com.cbr.university.controller;

import com.cbr.university.model.Group;
import com.cbr.university.model.Room;
import com.cbr.university.model.ScheduleLine;
import com.cbr.university.model.Teacher;
import com.cbr.university.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("editing/schedule-lines")
public class ScheduleLineController {
    private final BaseService<ScheduleLine> scheduleLineService;
    private final BaseService<Group> groupService;
    private final BaseService<Room> roomService;
    private final BaseService<Teacher> teacherService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute(scheduleLineService.getAll());
        return "editing/schedule-lines/view";
    }

    @GetMapping("get-add-view")
    public String getAddView(Model model) {
        model.addAttribute(groupService.getAll());
        model.addAttribute(teacherService.getAll());
        model.addAttribute(roomService.getAll());
        return "editing/schedule-lines/add";
    }

    @PostMapping("save-add-view")
    public String saveAddView(ScheduleLine scheduleLine) {
        scheduleLineService.create(scheduleLine);
        return "redirect:/editing/schedule-lines";
    }

    @GetMapping("get-edit-view/{id}")
    public String getEditView(@PathVariable int id, Model model) {
        model.addAttribute(scheduleLineService.getById(id));
        model.addAttribute(groupService.getAll());
        model.addAttribute(teacherService.getAll());
        model.addAttribute(roomService.getAll());
        return "editing/schedule-lines/edit";
    }

    @PostMapping("save-edit-view")
    public String saveEditView(ScheduleLine scheduleLine) {
        scheduleLineService.update(scheduleLine);
        return "redirect:/editing/schedule-lines";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        scheduleLineService.deleteById(id);
        return "redirect:/editing/schedule-lines";
    }
}
