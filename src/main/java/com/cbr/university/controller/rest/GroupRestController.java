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

import com.cbr.university.model.Group;
import com.cbr.university.service.BaseService;

@RestController
@RequestMapping("rest/groups")
public class GroupRestController {
    private static final String NAME_CUSTOM_HEADER = "X-Query-Result";
    private HttpHeaders headers = new HttpHeaders();
    private BaseService<Group> groupService;

    @Autowired
    public GroupRestController(BaseService<Group> groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAll() {
        List<Group> groups = groupService.getAll();
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "All objects Group found. Number of objects " + groups.size());
        return new ResponseEntity<>(groups, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Group> add(@RequestBody Group group) {
        Group createdGroup = groupService.create(group);
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Created Group object with id " + createdGroup.getId());
        return new ResponseEntity<>(createdGroup, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Group> update(@RequestBody Group group) {
        Group updatedGroup = groupService.update(group);
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Updated Group object with id " + updatedGroup.getId());
        return new ResponseEntity<>(updatedGroup, headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Group> delete(@PathVariable("id") int id) {
        groupService.delete(groupService.getById(id));
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Deleted Group object with id " + id);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
