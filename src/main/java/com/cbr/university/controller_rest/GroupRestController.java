package com.cbr.university.controller_rest;

import com.cbr.university.model.Group;
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
@RequestMapping("rest/groups")
@Validated
public class GroupRestController {
    private final BaseService<Group> groupService;

    public GroupRestController(BaseService<Group> groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getAll() {
        return groupService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Group add(@Validated(Create.class) @RequestBody Group group) {
        return groupService.create(group);
    }

    @PutMapping
    public Group update(@Validated(Update.class) @RequestBody Group group) {
        return groupService.update(group);
    }

    @DeleteMapping("{id}")
    public void delete(
            @NotNull(message = "Request must include a Group id")
            @IdExistsInDb(typeObject = "Group", message = "This Group id is not in the database")
            @PathVariable Integer id) {
        groupService.deleteById(id);
    }
}
