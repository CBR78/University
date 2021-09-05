package com.cbr.university.controller.rest;

import com.cbr.university.dto.GroupDto;
import com.cbr.university.model.Group;
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
@RequestMapping("rest/groups")
@Validated
public class GroupRestController {
    private static final String CUSTOM_HEADER_NAME = "X-Query-Result";
    private final HttpHeaders headers = new HttpHeaders();
    private final ModelMapper modelMapper;
    private final BaseService<Group> groupService;

    @Autowired
    public GroupRestController(BaseService<Group> groupService, ModelMapper modelMapper) {
        this.groupService = groupService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAll() {
        List<Group> groups = groupService.getAll();
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "All objects Group found. Number of objects " + groups.size());
        return new ResponseEntity<>(groups, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Group> add(@Validated(Create.class) @RequestBody GroupDto groupDto) {
        Group group = modelMapper.map(groupDto, Group.class);
        Group createdGroup = groupService.create(group);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Created Group object with id " + createdGroup.getId());
        return new ResponseEntity<>(createdGroup, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Group> update(
            @Validated(Update.class) @RequestBody GroupDto groupDto) {
        Group group = modelMapper.map(groupDto, Group.class);
        Group updatedGroup = groupService.update(group);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Updated Group object with id " + updatedGroup.getId());
        return new ResponseEntity<>(updatedGroup, headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Group> delete(
            @NotNull(message = "Request must include a Group id")
            @IdExistsInDb(typeObject = "Group", message = "This Group id is not in the database")
            @PathVariable Integer id) {
        groupService.delete(groupService.getById(id));
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Deleted Group object with id " + id);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
