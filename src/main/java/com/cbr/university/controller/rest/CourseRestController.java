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
import org.springframework.web.bind.annotation.RestController;

import com.cbr.university.model.Course;
import com.cbr.university.service.BaseService;

@RestController
@RequestMapping("rest/courses")
public class CourseRestController {
    private static final String NAME_CUSTOM_HEADER = "X-Query-Result";
    private HttpHeaders headers = new HttpHeaders();
    private BaseService<Course> courseService;

    @Autowired
    public CourseRestController(BaseService<Course> courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        List<Course> courses = courseService.getAll();
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "All objects Course found. Number of objects " + courses.size());
        return new ResponseEntity<>(courses, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> add(@RequestBody Course course) {
        Course createdCourse = courseService.create(course);
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Created Course object with id " + createdCourse.getId());
        return new ResponseEntity<>(createdCourse, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Course> update(@RequestBody Course course) {
        Course updatedCourse = courseService.update(course);
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Updated Course object with id " + updatedCourse.getId());
        return new ResponseEntity<>(updatedCourse, headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Course> delete(@PathVariable int id) {
        courseService.delete(courseService.getById(id));
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Deleted Course object with id " + id);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
