package com.cbr.university.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbr.university.model.Room;

public interface RoomRepositroy extends JpaRepository<Room, Integer> {

}
