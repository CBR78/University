package com.cbr.university.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbr.university.model.Course;

public interface CourseRepositroy extends JpaRepository<Course, Integer> {

}
