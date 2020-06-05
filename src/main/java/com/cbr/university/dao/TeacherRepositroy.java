package com.cbr.university.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbr.university.model.Teacher;

public interface TeacherRepositroy extends JpaRepository<Teacher, Integer> {

}
