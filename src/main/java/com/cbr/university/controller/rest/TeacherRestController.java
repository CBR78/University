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

import com.cbr.university.model.Teacher;
import com.cbr.university.service.BaseService;

@RestController
@RequestMapping("rest/teachers")
public class TeacherRestController {
    private static final String NAME_CUSTOM_HEADER = "X-Query-Result";
    private HttpHeaders headers = new HttpHeaders();
    private BaseService<Teacher> teacherService;

    @Autowired
    public TeacherRestController(BaseService<Teacher> teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAll() {
        List<Teacher> teachers = teacherService.getAll();
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER,
                "All objects Teacher found. Number of objects " + teachers.size());
        return new ResponseEntity<>(teachers, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Teacher> add(@RequestBody Teacher teacher) {
        Teacher createdTeacher = teacherService.create(teacher);
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Created Teacher object with id " + createdTeacher.getId());
        return new ResponseEntity<>(createdTeacher, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Teacher> update(@RequestBody Teacher teacher) {
        Teacher updatedTeacher = teacherService.update(teacher);
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Updated Teacher object with id " + updatedTeacher.getId());
        return new ResponseEntity<>(updatedTeacher, headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Teacher> delete(@PathVariable("id") int id) {
        teacherService.delete(teacherService.getById(id));
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Deleted Teacher object with id " + id);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
