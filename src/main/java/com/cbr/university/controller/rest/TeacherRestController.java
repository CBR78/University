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

import com.cbr.university.model.Teacher;
import com.cbr.university.service.BaseService;

@RestController
@RequestMapping("rest/teachers")
public class TeacherRestController {
    private BaseService<Teacher> teacherService;

    @Autowired
    public TeacherRestController(BaseService<Teacher> teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getAll() {
        return teacherService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher add(@RequestBody Teacher teacher) {
        return teacherService.create(teacher);
    }

    @PutMapping
    public Teacher update(@RequestBody Teacher teacher) {
        return teacherService.update(teacher);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {
        teacherService.delete(teacherService.getById(id));
    }
}
