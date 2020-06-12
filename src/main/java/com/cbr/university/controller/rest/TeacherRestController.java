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
    public ResponseEntity<List<Teacher>> getAll() {
        List<Teacher> teachers = teacherService.getAll();

        if (teachers != null && !teachers.isEmpty()) {
            return new ResponseEntity<>(teachers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Teacher teacher) {
        teacherService.create(teacher);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Teacher teacher) {
        teacherService.update(teacher);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        teacherService.delete(teacherService.getById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
