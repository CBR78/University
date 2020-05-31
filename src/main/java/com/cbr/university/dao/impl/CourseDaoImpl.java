package com.cbr.university.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.model.Course;

@Repository
@Transactional
public class CourseDaoImpl implements BaseDao<Course> {
    private SessionFactory sessionFactory;

    @Autowired
    public CourseDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Course course) {
        sessionFactory.getCurrentSession().save(course);
    }

    @Override
    public void update(Course course) {
        sessionFactory.getCurrentSession().update(course);
    }

    @Override
    public void delete(Course course) {
        sessionFactory.getCurrentSession().delete(course);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Course> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Course").list();
    }

    @Override
    public Course getById(int id) {
        return sessionFactory.getCurrentSession().get(Course.class, id);
    }
}
