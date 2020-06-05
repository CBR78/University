package com.cbr.university.repository;

import org.springframework.data.repository.CrudRepository;

import com.cbr.university.model.Course;

public interface CourseRepositroy extends CrudRepository<Course, Integer> {

}
