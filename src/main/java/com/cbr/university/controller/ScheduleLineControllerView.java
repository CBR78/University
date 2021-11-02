package com.cbr.university.controller;

import com.cbr.university.model.ScheduleLine;
import com.cbr.university.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("viewing")
public class ScheduleLineControllerView {
    private final ModelAndView mv = new ModelAndView();
    private final BaseService<ScheduleLine> scheduleLineService;

    @Autowired
    public ScheduleLineControllerView(BaseService<ScheduleLine> scheduleLineService) {
        this.scheduleLineService = scheduleLineService;
    }

    @GetMapping
    public ModelAndView getAllView() {
        mv.clear();
        mv.setViewName("viewing/schedule-lines/view");
        mv.addObject("scheduleLines", scheduleLineService.getAll());
        return mv;
    }
}
