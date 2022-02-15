package com.cbr.university.controller_rest;

import com.cbr.university.model.Group;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.Update;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("rest/groups")
@Validated
public class GroupRestController {
    private static final String CUSTOM_HEADER_NAME = "X-Query-Result";
    private final BaseService<Group> groupService;

    public GroupRestController(BaseService<Group> groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAll() {
        List<Group> groups = groupService.getAll();
        return ResponseEntity
                .ok()
                .header(CUSTOM_HEADER_NAME, "All objects Group found. Number of objects " + groups.size())
                .body(groups);
    }

    @PostMapping
    public ResponseEntity<Group> add(@Validated(Create.class) @RequestBody Group group) {
        Group createdGroup = groupService.create(group);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(CUSTOM_HEADER_NAME, "Created Group object with id " + createdGroup.getId())
                .body(createdGroup);
    }

    @PutMapping
    public ResponseEntity<Group> update(@Validated(Update.class) @RequestBody Group group) {
        Group updatedGroup = groupService.update(group);
        return ResponseEntity
                .ok()
                .header(CUSTOM_HEADER_NAME, "Updated Group object with id " + updatedGroup.getId())
                .body(updatedGroup);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Group> delete(
            @NotNull(message = "Request must include a Group id")
            @IdExistsInDb(typeObject = "Group", message = "This Group id is not in the database")
            @PathVariable Integer id) {
        groupService.deleteById(id);
        return ResponseEntity
                .ok()
                .header(CUSTOM_HEADER_NAME, "Deleted Group object with id " + id)
                .build();
    }
}
