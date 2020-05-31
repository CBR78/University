package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.model.Student;
import com.cbr.university.service.BaseService;

@Service
public class StudentServiceImpl implements BaseService<Student> {

    private BaseDao<Student> studentDao;

    @Autowired
    public StudentServiceImpl(BaseDao<Student> studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void create(Student student) {
        studentDao.create(student);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void delete(Student student) {
        studentDao.delete(student);
    }

    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }

    @Override
    public Student getById(int id) {
        return studentDao.getById(id);
    }
}
