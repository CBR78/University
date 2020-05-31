package com.cbr.university.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.model.Teacher;

@Repository
@Transactional
public class TeacherDaoImpl implements BaseDao<Teacher> {
    private SessionFactory sessionFactory;

    @Autowired
    public TeacherDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Teacher teacher) {
        sessionFactory.getCurrentSession().save(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        sessionFactory.getCurrentSession().update(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        sessionFactory.getCurrentSession().delete(teacher);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Teacher> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Teacher").list();
    }

    @Override
    public Teacher getById(int id) {
        return sessionFactory.getCurrentSession().get(Teacher.class, id);
    }
}
