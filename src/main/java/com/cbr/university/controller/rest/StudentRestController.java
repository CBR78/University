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

import com.cbr.university.model.Student;
import com.cbr.university.service.BaseService;

@RestController
@RequestMapping("rest/students")
public class StudentRestController {
    private static final String NAME_CUSTOM_HEADER = "X-Query-Result";
    private HttpHeaders headers = new HttpHeaders();
    private BaseService<Student> studentService;

    @Autowired
    public StudentRestController(BaseService<Student> studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        List<Student> students = studentService.getAll();
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER,
                "All objects Student found. Number of objects " + students.size());
        return new ResponseEntity<>(students, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> add(@RequestBody Student student) {
        Student createdStudent = studentService.create(student);
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Created Student object with id " + createdStudent.getId());
        return new ResponseEntity<>(createdStudent, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student) {
        Student updatedStudent = studentService.update(student);
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Updated Student object with id " + updatedStudent.getId());
        return new ResponseEntity<>(updatedStudent, headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> delete(@PathVariable("id") int id) {
        studentService.delete(studentService.getById(id));
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Deleted Student object with id " + id);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
