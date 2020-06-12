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

import com.cbr.university.model.Course;
import com.cbr.university.service.BaseService;

@RestController
@RequestMapping("rest/courses")
public class CourseRestController {
    private BaseService<Course> courseService;

    @Autowired
    public CourseRestController(BaseService<Course> courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        List<Course> courses = courseService.getAll();

        if (courses != null && !courses.isEmpty()) {
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Course course) {
        courseService.create(course);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Course course) {
        courseService.update(course);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        courseService.delete(courseService.getById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
