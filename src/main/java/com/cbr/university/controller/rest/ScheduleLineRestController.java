package com.cbr.university.controller.rest;

import com.cbr.university.dto.ScheduleLineDtoRest;
import com.cbr.university.model.ScheduleLine;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.Update;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("rest/scheduleLines")
@Validated
public class ScheduleLineRestController {
    private static final String CUSTOM_HEADER_NAME = "X-Query-Result";
    private final HttpHeaders headers = new HttpHeaders();
    private final ModelMapper modelMapper;
    private final BaseService<ScheduleLine> scheduleLineService;

    @Autowired
    public ScheduleLineRestController(BaseService<ScheduleLine> scheduleLineService,
                                      ModelMapper modelMapper) {
        this.scheduleLineService = scheduleLineService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<ScheduleLine>> getAll() {
        List<ScheduleLine> scheduleLines = scheduleLineService.getAll();
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME,
                "All objects ScheduleLine found. Number of objects " + scheduleLines.size());
        return new ResponseEntity<>(scheduleLines, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ScheduleLine> add(
            @Validated(Create.class) @RequestBody ScheduleLineDtoRest scheduleLineDtoRest) {
        ScheduleLine scheduleLine = modelMapper.map(scheduleLineDtoRest, ScheduleLine.class);
        ScheduleLine createdScheduleLine = scheduleLineService.create(scheduleLine);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME,
                "Created ScheduleLine object with id " + createdScheduleLine.getId());
        return new ResponseEntity<>(createdScheduleLine, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ScheduleLine> update(
            @Validated(Update.class) @RequestBody ScheduleLineDtoRest scheduleLineDtoRest) {
        ScheduleLine scheduleLine = modelMapper.map(scheduleLineDtoRest, ScheduleLine.class);
        ScheduleLine updatedScheduleLine = scheduleLineService.update(scheduleLine);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME,
                "Updated ScheduleLine object with id " + updatedScheduleLine.getId());
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
