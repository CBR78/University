package com.cbr.university.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.model.Group;

@Repository
@Transactional
public class GroupDaoImpl implements BaseDao<Group> {
    private SessionFactory sessionFactory;

    @Autowired
    public GroupDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Group group) {
        sessionFactory.getCurrentSession().save(group);
    }

    @Override
    public void update(Group group) {
        sessionFactory.getCurrentSession().update(group);
    }

    @Override
    public void delete(Group group) {
        sessionFactory.getCurrentSession().delete(group);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Group> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Group").list();
    }

    @Override
    public Group getById(int id) {
        return sessionFactory.getCurrentSession().get(Group.class, id);
    }
}
