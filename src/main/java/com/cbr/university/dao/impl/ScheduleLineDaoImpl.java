package com.cbr.university.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.model.ScheduleLine;

@Repository
@Transactional
public class ScheduleLineDaoImpl implements BaseDao<ScheduleLine> {
    private SessionFactory sessionFactory;

    @Autowired
    public ScheduleLineDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(ScheduleLine scheduleLine) {
        sessionFactory.getCurrentSession().save(scheduleLine);
    }

    @Override
    public void update(ScheduleLine scheduleLine) {
        sessionFactory.getCurrentSession().update(scheduleLine);
    }

    @Override
    public void delete(ScheduleLine scheduleLine) {
        sessionFactory.getCurrentSession().delete(scheduleLine);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ScheduleLine> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from ScheduleLine").list();
    }

    @Override
    public ScheduleLine getById(int id) {
        return sessionFactory.getCurrentSession().get(ScheduleLine.class, id);
    }
}
