package com.cbr.university.repository;

import org.springframework.data.repository.CrudRepository;

import com.cbr.university.model.Student;

public interface StudentRepositroy extends CrudRepository<Student, Integer> {

}
