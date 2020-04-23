package com.cbr.university.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RoomMapper implements RowMapper<Room> {
    
    public Room mapRow(ResultSet resultSet, int i) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getInt("room_id"));
        room.setName(resultSet.getString("room_name"));
        return room;
    }
}
