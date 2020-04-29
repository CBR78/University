package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.impl.RoomDaoImpl;
import com.cbr.university.model.Room;
import com.cbr.university.service.BaseService;

@Service
public class RoomServiceImpl implements BaseService<Room> {

    private RoomDaoImpl roomDaoImpl;

    @Autowired
    public RoomServiceImpl(RoomDaoImpl roomDaoImpl) {
        this.roomDaoImpl = roomDaoImpl;
    }

    @Override
    public void create(Room room) {
        roomDaoImpl.create(room);
    }

    @Override
    public void update(Room room) {
        roomDaoImpl.update(room);
    }

    @Override
    public void delete(Room room) {
        roomDaoImpl.delete(room);
    }

    @Override
    public List<Room> getAll() {
        return roomDaoImpl.getAll();
    }

    @Override
    public Room getById(int id) {
        return roomDaoImpl.getById(id);
    }
}
