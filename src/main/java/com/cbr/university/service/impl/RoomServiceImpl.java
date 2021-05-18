package com.cbr.university.service.impl;

import com.cbr.university.model.Room;
import com.cbr.university.repository.RoomRepository;
import com.cbr.university.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class RoomServiceImpl implements BaseService<Room> {

    private final RoomRepository roomRepository;
    private final EntityManager entityManager;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, EntityManager entityManager) {
        this.roomRepository = roomRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room update(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void delete(Room room) {
        roomRepository.delete(room);
    }

    @Override
    public List<Room> getAll() {
        entityManager.clear();
        return roomRepository.findAll();
    }

    @Override
    public Room getById(int id) {
        return roomRepository.findById(id).get();
    }

    @Override
    public boolean existsById(int id) {
        return roomRepository.existsById(id);
    }
}
