package com.cbr.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbr.university.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
