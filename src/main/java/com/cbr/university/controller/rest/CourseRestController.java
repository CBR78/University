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
    public List<Course> getAll() {
        return courseService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course add(@RequestBody Course course) {
        return courseService.create(course);
    }

    @PutMapping
    public Course update(@RequestBody Course course) {
        return courseService.update(course);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {
        courseService.delete(courseService.getById(id));
    }
}
