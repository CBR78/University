package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.model.Student;
import com.cbr.university.repository.StudentRepository;
import com.cbr.university.service.BaseService;

@Service
public class StudentServiceImpl implements BaseService<Student> {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void create(Student student) {
        studentRepository.saveAndFlush(student);
    }

    @Override
    public void update(Student student) {
        studentRepository.saveAndFlush(student);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getById(int id) {
        return studentRepository.findById(id).get();
    }
}
