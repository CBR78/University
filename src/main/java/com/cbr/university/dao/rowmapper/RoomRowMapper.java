package com.cbr.university.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cbr.university.model.Room;

public class RoomRowMapper implements RowMapper<Room> {
    
    public Room mapRow(ResultSet resultSet, int i) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getInt("room_id"));
        room.setName(resultSet.getString("room_name"));
        return room;
    }
}
