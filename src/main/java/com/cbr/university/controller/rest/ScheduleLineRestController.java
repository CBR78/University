package com.cbr.university.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<List<ScheduleLine>> getAll() {
        List<ScheduleLine> scheduleLines = scheduleLineService.getAll();

        if (scheduleLines != null && !scheduleLines.isEmpty()) {
            return new ResponseEntity<>(scheduleLines, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ScheduleLine scheduleLine) {
        scheduleLineService.create(scheduleLine);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ScheduleLine scheduleLine) {
        scheduleLineService.update(scheduleLine);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        scheduleLineService.delete(scheduleLineService.getById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
