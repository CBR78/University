package com.cbr.university.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cbr.university.model.Room;
import com.cbr.university.service.BaseService;

@RestController
@RequestMapping("rest/rooms")
public class RoomRestController {
    private BaseService<Room> roomService;

    @Autowired
    public RoomRestController(BaseService<Room> roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAll() {
        return roomService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room add(@RequestBody Room room) {
        return roomService.create(room);
    }

    @PutMapping
    public Room update(@RequestBody Room room) {
        return roomService.update(room);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {
        roomService.delete(roomService.getById(id));
    }
}
