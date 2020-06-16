package com.cbr.university.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cbr.university.model.ScheduleLine;
import com.cbr.university.service.BaseService;

@RestController
@RequestMapping("rest/scheduleLines")
public class ScheduleLineRestController {
    private BaseService<ScheduleLine> scheduleLineService;

    @Autowired
    public ScheduleLineRestController(BaseService<ScheduleLine> scheduleLineService) {
        this.scheduleLineService = scheduleLineService;
    }

    @GetMapping
    public List<ScheduleLine> getAll() {
        return scheduleLineService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleLine add(@RequestBody ScheduleLine scheduleLine) {
        return scheduleLineService.create(scheduleLine);
    }

    @PutMapping
    public ScheduleLine update(@RequestBody ScheduleLine scheduleLine) {
        return scheduleLineService.update(scheduleLine);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {
        scheduleLineService.delete(scheduleLineService.getById(id));
    }
}
