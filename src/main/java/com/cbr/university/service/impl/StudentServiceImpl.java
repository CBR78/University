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
    public void update(Student t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Student t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Student> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Student getById(int id) {
        // TODO Auto-generated method stub
        return null;
    }
}
