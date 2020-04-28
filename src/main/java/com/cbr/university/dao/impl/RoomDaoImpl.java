package com.cbr.university.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.dao.mappers.RoomMapper;
import com.cbr.university.model.Room;

@Component
public class RoomDaoImpl implements BaseDao<Room> {
    private static final String SQL_INSERT = "INSERT INTO rooms (room_name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE rooms SET room_name = ? WHERE room_id = ?";
    private static final String SQL_DELETE = "DELETE FROM rooms WHERE room_id = ?";
    private static final String SQL_GET_ALL = "SELECT * FROM rooms";
    private static final String SQL_GET_BY_ID = "SELECT * FROM rooms WHERE room_id = ?";
    JdbcTemplate jdbcTemplate;

    @Autowired
    public RoomDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Room room) {
        jdbcTemplate.update(SQL_INSERT, room.getName());
    }

    public void update(Room room) {
        jdbcTemplate.update(SQL_UPDATE, room.getName(), room.getId());
    }

    public void delete(Room room) {
        jdbcTemplate.update(SQL_DELETE, room.getId());
    }

    public List<Room> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new RoomMapper());
    }

    public Room getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new RoomMapper());
    }
}
