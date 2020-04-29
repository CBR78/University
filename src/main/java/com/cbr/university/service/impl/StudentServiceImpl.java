package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.impl.StudentDaoImpl;
import com.cbr.university.model.Student;
import com.cbr.university.service.BaseService;

@Service
public class StudentServiceImpl implements BaseService<Student> {

    private StudentDaoImpl studentDaoImpl;

    @Autowired
    public StudentServiceImpl(StudentDaoImpl studentDaoImpl) {
        this.studentDaoImpl = studentDaoImpl;
    }

    @Override
    public void create(Student student) {
        studentDaoImpl.create(student);
    }

    @Override
    public void update(Student student) {
        studentDaoImpl.update(student);
    }

    @Override
    public void delete(Student student) {
        studentDaoImpl.delete(student);
    }

    @Override
    public List<Student> getAll() {
        return studentDaoImpl.getAll();
    }

    @Override
    public Student getById(int id) {
        return studentDaoImpl.getById(id);
    }

    public List<Student> getByGroup(int groupId) {
        return studentDaoImpl.getByGroup(groupId);
    }
}
