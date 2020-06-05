package com.cbr.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbr.university.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

}
