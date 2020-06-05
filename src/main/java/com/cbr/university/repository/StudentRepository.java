package com.cbr.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbr.university.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
