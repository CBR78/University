package com.cbr.university.service;

import com.cbr.university.model.Room;
import com.cbr.university.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements BaseService<Room> {
    private final RoomRepository roomRepository;
    private final EntityManager entityManager;

    @Override
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room update(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteById(int id) {
        roomRepository.deleteById(id);
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
