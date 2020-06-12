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

import com.cbr.university.model.Group;
import com.cbr.university.service.BaseService;

@RestController
@RequestMapping("rest/groups")
public class GroupRestController {
    private BaseService<Group> groupService;

    @Autowired
    public GroupRestController(BaseService<Group> groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAll() {
        List<Group> groups = groupService.getAll();

        if (groups != null && !groups.isEmpty()) {
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Group group) {
        groupService.create(group);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Group group) {
        groupService.update(group);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        groupService.delete(groupService.getById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
