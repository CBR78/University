package com.cbr.university.controller;

import com.cbr.university.model.ScheduleLine;
import com.cbr.university.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("viewing")
public class ScheduleLineControllerView {
    private final BaseService<ScheduleLine> scheduleLineService;

    public ScheduleLineControllerView(BaseService<ScheduleLine> scheduleLineService) {
        this.scheduleLineService = scheduleLineService;
    }

    @GetMapping
    public String getAllView(Model model) {
        model.addAttribute(scheduleLineService.getAll());
        return "viewing/schedule-lines/view";
    }
}
