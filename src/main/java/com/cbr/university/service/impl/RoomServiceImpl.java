package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.model.Room;
import com.cbr.university.service.BaseService;

@Service
public class RoomServiceImpl implements BaseService<Room> {

    private BaseDao<Room> roomDao;

    @Autowired
    public RoomServiceImpl(BaseDao<Room> roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public void create(Room room) {
        roomDao.create(room);
    }

    @Override
    public void update(Room room) {
        roomDao.update(room);
    }

    @Override
    public void delete(Room room) {
        roomDao.delete(room);
    }

    @Override
    public List<Room> getAll() {
        return roomDao.getAll();
    }

    @Override
    public Room getById(int id) {
        return roomDao.getById(id);
    }
}
