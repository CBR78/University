package com.cbr.university.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cbr.university.model.Room;
import com.cbr.university.model.RoomMapper;

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

    public boolean create(Room room) {
        return jdbcTemplate.update(SQL_INSERT, room.getName()) > 0;
    }

    public boolean update(Room room) {
        return jdbcTemplate.update(SQL_UPDATE, room.getName(), room.getId()) > 0;
    }

    public boolean delete(Room room) {
        return jdbcTemplate.update(SQL_DELETE, room.getId()) > 0;
    }

    public List<Room> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new RoomMapper());
    }

    public Room getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new RoomMapper());
    }
}
