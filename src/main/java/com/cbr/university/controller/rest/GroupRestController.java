package com.cbr.university.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private BaseService<Group> groupService;

    @Autowired
    public GroupRestController(BaseService<Group> groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getAll() {
        return groupService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Group add(@RequestBody Group group) {
        return groupService.create(group);
    }

    @PutMapping
    public Group update(@RequestBody Group group) {
        return groupService.update(group);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {
        groupService.delete(groupService.getById(id));
    }
}
