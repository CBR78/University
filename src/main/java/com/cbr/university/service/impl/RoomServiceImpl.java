package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.RoomRepositroy;
import com.cbr.university.model.Room;
import com.cbr.university.service.BaseService;

@Service
public class RoomServiceImpl implements BaseService<Room> {

    private RoomRepositroy roomDao;

    @Autowired
    public RoomServiceImpl(RoomRepositroy roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public void create(Room room) {
        roomDao.saveAndFlush(room);
    }

    @Override
    public void update(Room room) {
        roomDao.saveAndFlush(room);
    }

    @Override
    public void delete(Room room) {
        roomDao.delete(room);
    }

    @Override
    public List<Room> getAll() {
        return (List<Room>) roomDao.findAll();
    }

    @Override
    public Room getById(int id) {
        return roomDao.findById(id).get();
    }
}
