package com.cbr.university.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.model.Room;

@Repository
@Transactional
public class RoomDaoImpl implements BaseDao<Room> {
    private SessionFactory sessionFactory;

    @Autowired
    public RoomDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Room room) {
        sessionFactory.getCurrentSession().save(room);
    }

    @Override
    public void update(Room room) {
        sessionFactory.getCurrentSession().update(room);
    }

    @Override
    public void delete(Room room) {
        sessionFactory.getCurrentSession().delete(room);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Room> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Room").list();
    }

    @Override
    public Room getById(int id) {
        return sessionFactory.getCurrentSession().get(Room.class, id);
    }
}
