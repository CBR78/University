package com.cbr.university.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.dao.rowmapper.RoomRowMapper;
import com.cbr.university.model.Room;

@Repository
public class RoomDaoImpl implements BaseDao<Room> {
    private static final String SQL_INSERT = "INSERT INTO rooms (room_name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE rooms SET room_name = ? WHERE room_id = ?";
    private static final String SQL_DELETE = "DELETE FROM rooms WHERE room_id = ?";
    private static final String SQL_GET_ALL = "SELECT room_id, room_name FROM rooms";
    private static final String SQL_GET_BY_ID = "SELECT room_id, room_name FROM rooms WHERE room_id = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RoomDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Room room) {
        jdbcTemplate.update(SQL_INSERT, room.getName());
    }

    @Override
    public void update(Room room) {
        jdbcTemplate.update(SQL_UPDATE, room.getName(), room.getId());
    }

    @Override
    public void delete(Room room) {
        jdbcTemplate.update(SQL_DELETE, room.getId());
    }

    @Override
    public List<Room> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new RoomRowMapper());
    }

    @Override
    public Room getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new RoomRowMapper());
    }
}
