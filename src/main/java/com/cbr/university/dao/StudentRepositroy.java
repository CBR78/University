package com.cbr.university.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbr.university.model.Student;

public interface StudentRepositroy extends JpaRepository<Student, Integer> {

}
