package com.cbr.university.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private static final String NAME_CUSTOM_HEADER = "X-Query-Result";
    private HttpHeaders headers = new HttpHeaders();
    private BaseService<ScheduleLine> scheduleLineService;

    @Autowired
    public ScheduleLineRestController(BaseService<ScheduleLine> scheduleLineService) {
        this.scheduleLineService = scheduleLineService;
    }

    @GetMapping
    public ResponseEntity<List<ScheduleLine>> getAll() {
        List<ScheduleLine> scheduleLines = scheduleLineService.getAll();
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER,
                "All objects ScheduleLine found. Number of objects " + scheduleLines.size());
        return new ResponseEntity<>(scheduleLines, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ScheduleLine> add(@RequestBody ScheduleLine scheduleLine) {
        ScheduleLine createdScheduleLine = scheduleLineService.create(scheduleLine);
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER,
                "Created ScheduleLine object with id " + createdScheduleLine.getId());
        return new ResponseEntity<>(createdScheduleLine, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ScheduleLine> update(@RequestBody ScheduleLine scheduleLine) {
        ScheduleLine updatedScheduleLine = scheduleLineService.update(scheduleLine);
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER,
                "Updated ScheduleLine object with id " + updatedScheduleLine.getId());
        return new ResponseEntity<>(updatedScheduleLine, headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ScheduleLine> delete(@PathVariable("id") int id) {
        scheduleLineService.delete(scheduleLineService.getById(id));
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Deleted ScheduleLine object with id " + id);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
