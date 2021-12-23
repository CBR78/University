package com.cbr.university.controller_rest;

import com.cbr.university.model.ScheduleLine;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.Update;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("rest/scheduleLines")
@Validated
public class ScheduleLineRestController {
    private static final String CUSTOM_HEADER_NAME = "X-Query-Result";
    private final HttpHeaders headers = new HttpHeaders();
    private final BaseService<ScheduleLine> scheduleLineService;

    public ScheduleLineRestController(BaseService<ScheduleLine> scheduleLineService) {
        this.scheduleLineService = scheduleLineService;
    }

    @GetMapping
    public ResponseEntity<List<ScheduleLine>> getAll() {
        List<ScheduleLine> scheduleLines = scheduleLineService.getAll();
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "All objects ScheduleLine found. Number of objects " + scheduleLines.size());
        return new ResponseEntity<>(scheduleLines, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ScheduleLine> add(@Validated(Create.class) @RequestBody ScheduleLine scheduleLine) {
        ScheduleLine createdScheduleLine = scheduleLineService.create(scheduleLine);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Created ScheduleLine object with id " + createdScheduleLine.getId());
        return new ResponseEntity<>(createdScheduleLine, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ScheduleLine> update(@Validated(Update.class) @RequestBody ScheduleLine scheduleLine) {
        ScheduleLine updatedScheduleLine = scheduleLineService.update(scheduleLine);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Updated ScheduleLine object with id " + updatedScheduleLine.getId());
        return new ResponseEntity<>(updatedScheduleLine, headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ScheduleLine> delete(
            @NotNull(message = "Request must include a ScheduleLine id")
            @IdExistsInDb(typeObject = "ScheduleLine", message = "This ScheduleLine id is not in the database")
            @PathVariable Integer id) {
        scheduleLineService.delete(scheduleLineService.getById(id));
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Deleted ScheduleLine object with id " + id);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
