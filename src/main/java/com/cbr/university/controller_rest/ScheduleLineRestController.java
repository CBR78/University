package com.cbr.university.controller_rest;

import com.cbr.university.model.ScheduleLine;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.Update;
import org.springframework.http.HttpStatus;
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
    private final BaseService<ScheduleLine> scheduleLineService;

    public ScheduleLineRestController(BaseService<ScheduleLine> scheduleLineService) {
        this.scheduleLineService = scheduleLineService;
    }

    @GetMapping
    public List<ScheduleLine> getAll() {
        return scheduleLineService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleLine add(@Validated(Create.class) @RequestBody ScheduleLine scheduleLine) {
        return scheduleLineService.create(scheduleLine);
    }

    @PutMapping
    public ScheduleLine update(@Validated(Update.class) @RequestBody ScheduleLine scheduleLine) {
        return scheduleLineService.update(scheduleLine);
    }

    @DeleteMapping("{id}")
    public void delete(
            @NotNull(message = "Request must include a ScheduleLine id")
            @IdExistsInDb(typeObject = "ScheduleLine", message = "This ScheduleLine id is not in the database")
            @PathVariable Integer id) {
        scheduleLineService.deleteById(id);
    }
}
