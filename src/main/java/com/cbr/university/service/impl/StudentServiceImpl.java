package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.StudentRepositroy;
import com.cbr.university.model.Student;
import com.cbr.university.service.BaseService;

@Service
public class StudentServiceImpl implements BaseService<Student> {

    private StudentRepositroy studentDao;

    @Autowired
    public StudentServiceImpl(StudentRepositroy studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void create(Student student) {
        studentDao.saveAndFlush(student);
    }

    @Override
    public void update(Student student) {
        studentDao.saveAndFlush(student);
    }

    @Override
    public void delete(Student student) {
        studentDao.delete(student);
    }

    @Override
    public List<Student> getAll() {
        return (List<Student>) studentDao.findAll();
    }

    @Override
    public Student getById(int id) {
        return studentDao.findById(id).get();
    }
}
