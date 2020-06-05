package com.cbr.university.repository;

import org.springframework.data.repository.CrudRepository;

import com.cbr.university.model.Teacher;

public interface TeacherRepositroy extends CrudRepository<Teacher, Integer> {

}
