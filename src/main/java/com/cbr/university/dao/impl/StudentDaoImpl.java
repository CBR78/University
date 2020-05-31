package com.cbr.university.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.model.Student;

@Repository
@Transactional
public class StudentDaoImpl implements BaseDao<Student> {
    private SessionFactory sessionFactory;

    @Autowired
    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Student student) {
        sessionFactory.getCurrentSession().save(student);
    }

    @Override
    public void update(Student student) {
        sessionFactory.getCurrentSession().update(student);
    }

    @Override
    public void delete(Student student) {
        sessionFactory.getCurrentSession().delete(student);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Student").list();
    }

    @Override
    public Student getById(int id) {
        return sessionFactory.getCurrentSession().get(Student.class, id);
    }
}
